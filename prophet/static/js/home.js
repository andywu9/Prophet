"use strict";
/*jslint browser: true, continue:true */
/*global Chart, $, jQuery, alert, coin_table, historical_table*/

Number.prototype.formatMoney = function (c, d, t) {
    var n, s, i, j;

    c = Math.abs(c);
    c = isNaN(c) ? 2 : c;
    d = d === undefined ? "." : d;
    t = t === undefined ? "," : t;
    n = Math.abs(Number(this) || 0).toFixed(c);
    s = n < 0 ? "-" : "";
    i = String(parseInt(n, 10));
    j = i.length;

    j = j > 3 ? j % 3 : 0;
    return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
};

var createMainTableGraph = function (coin_name, historical_data, ctx) {
    /*
        This function creates the graph for a given coin using the provided context
    */

    var graph_data = [],
        time_labels = [],
        data,
        point,
        chart;

    for (data in historical_data[coin_name]) {
        if (historical_data[coin_name].hasOwnProperty(data)) {
            point = {
                x : historical_data[coin_name][data].datetime,
                y : historical_data[coin_name][data].historical_price,
            };

            graph_data.push(point);
            time_labels.push(historical_data[coin_name][data].datetime);
        }
    }

    chart = new Chart(ctx, {
        // The type of chart we want to create
        type: 'line',

        // The data for our dataset
        data: {
            labels: time_labels,
            datasets: [{
                radius: 0,
                fill: false,
                borderColor: 'rgb(255, 99, 132)',
                data: graph_data,
            }]
        },

        // Configuration options go here
        options: {
            responsive: false,
            animation: false,
            legend: {
                display: false,
            },
            scales: {
                xAxes: [{
                    type: 'time',
                    display: false,
                    ticks: {
                        source: 'data',
                        autoSkip: false,
                    },
                }],
                yAxes: [{
                    display: false,
                }],
            },
            tooltips: {
                enabled: false,
            },
            hover: {
                mode: null,
            }
        }
    });
};

var loadGraph = function (coin_name, modal, date_restrict) {
    var historical_data = JSON.parse(historical_table),
        historical_date,
        point,
        graph_data = [],
        time_labels = [],
        data,
        canvas,
        ctx,
        chart;

    for (data in historical_data[coin_name]) {
        if (historical_data[coin_name].hasOwnProperty(data)) {

            //skip data that is before desired date
            historical_date = new Date(historical_data[coin_name][data].datetime);
            if (date_restrict !== undefined && historical_date < date_restrict) {
                continue;
            }

            point = {
                x : historical_data[coin_name][data].datetime,
                y : historical_data[coin_name][data].historical_price,
            };

            graph_data.push(point);
            time_labels.push(historical_data[coin_name][data].datetime);
        }
    }

    canvas = modal.find('canvas').get(0);
    ctx = canvas.getContext('2d');
    chart = new Chart(ctx, {
        // The type of chart we want to create
        type: 'line',

        // The data for our dataset
        data: {
            labels: time_labels,
            datasets: [{
                radius: 0,
                fill: false,
                borderColor: 'rgb(255, 99, 132)',
                data: graph_data,
            }]
        },

        // Configuration options go here
        options: {
            responsive: true,
            legend: {
                display: false,
            },
            scales: {
                xAxes: [{
                    type: 'time',
                    ticks: {
                        source: 'data',
                        autoSkip: false,
                    },
                }],
            },
            hover: {
                mode: 'nearest',
                intersect: false
            },
            tooltips: {
                mode: 'nearest',
                interesect: false,
            },
        }
    });
};

var loadModalData = function (coin_name, modal) {
    var modal_table = modal.find("#modal-coin-data").get(0),
        title_cell = modal.find('#modal-title').get(0),
        title = document.createElement('h3');

    title.classList.add('modal-header');

    title.appendChild(document.createTextNode(coin_name));
    title_cell.appendChild(title);

    
};

//Add favorite buttons that done actually favorite
$(function () {
    $(".favstar").hover(function () {
        $(this).attr('src', 'static/images/GoldStar.png');
    }, function () {
        $(this).attr('src', 'static/images/EmptyStar.png');
    });
});

var resetCanvas = function (modal) {
    var canvas;

    modal.find('canvas').get(0).remove();
    modal.find('iframe').remove();
    canvas = document.createElement('canvas');

    modal.find('#modal-graph-cell').get(0).appendChild(canvas);
};

var openHistory = function (evt, days_back) {
    var date_restrict = new Date(),
        modal = $('#myModal'),
        tablinks = $(".tablinks"),
        i;

    date_restrict = new Date(date_restrict.getTime() - days_back * 24 * 60 * 60 * 1000);

    resetCanvas(modal);
    loadGraph(modal.get(0).getAttribute('coin'), modal, date_restrict);

    for (i = 0; i < tablinks.length; i += 1) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    evt.currentTarget.className += " active";
};

$(document).ready(function () {
    /*
        This function creates the main page data table, including creating HTML elements,
        populating the elements with data, and creating and populating the graph. This does too much, separate
        the code better. 
    */

    //Retrieve database data
    var coin_data = JSON.parse(coin_table),
        historical_data = JSON.parse(historical_table),
        data_type = ['pk', 'price', 'market_cap', 'volume', 'price_change_day'],
        tbdy = document.createElement('tbody'),
        thead = document.createElement('thead'),
        trh = document.createElement('tr'),
        header_titles = ['Coin', 'Current Price', 'Market Cap', 'Volume (24h)', 'Price Change (24h)', 'Price History', 'Favorite'],
        tr,
        th,
        td,
        coin_name,
        cell_div,
        data,
        graph,
        tdgraph,
        ctx,
        tdfav,
        starimg,
        i,
        j,

        //Create container and table elements
        body = document.getElementsByClassName('table-container')[0],
        tbl = document.createElement('table');

    //Add table classes
    tbl.classList.add('table');
    tbl.classList.add('table-hover');
    tbl.classList.add('table-striped');
    tbl.classList.add('table-sorter')
    tbl.setAttribute('id', 'coins');

    //Create header row
    trh.setAttribute('id', 'header-row');

    //Create header cells
    for (i = 0; i < 7; i += 1) {
        th = document.createElement('th');
        if (header_titles[i] === 'Price History' || header_titles[i] === 'Favorite') {
            th.setAttribute('data-sorter', false);
        }

        th.appendChild(document.createTextNode(header_titles[i]));
        trh.appendChild(th);
    }

    //Add header row to table body
    thead.appendChild(trh);
    tbl.appendChild(thead);

    //Create each coin's row
    for (i = 0; i < 20; i += 1) {
        tr = document.createElement('tr');
        td = document.createElement('td');
        coin_name = coin_data[i][data_type[0]];

        tr.setAttribute('id', coin_name);
        tr.classList.add('clickable-row');

        cell_div = document.createElement('div');
        cell_div.classList.add('nowrap');
        cell_div.appendChild(document.createTextNode(coin_name));

        td.appendChild(cell_div);
        tr.appendChild(td);
        for (j = 1; j < data_type.length; j += 1) {
            td = document.createElement('td');
            data = coin_data[i].fields[data_type[j]];

            switch (data_type[j]) {
            case 'price':
                data = parseFloat(data);
                data = '$ ' + data.formatMoney(2);
                break;
            case 'market_cap':
            case 'volume':
                data = parseFloat(data);
                data = '$ ' + data.formatMoney(0);
                break;
            case 'price_change_day':
                data = data + '%';
                break;
            }
            cell_div = document.createElement('div');
            cell_div.classList.add('nowrap');
            cell_div.appendChild(document.createTextNode(data));

            td.appendChild(cell_div);
            tr.appendChild(td);
        }

        tdgraph = document.createElement('td');
        graph = document.createElement('canvas');
        graph.classList.add('graph');
        graph.setAttribute('width', '300');
        graph.setAttribute('height', '100');

        //Create Graph
        ctx = graph.getContext('2d');
        createMainTableGraph(coin_name, historical_data, ctx);

        tdgraph.appendChild(graph);
        tr.appendChild(tdgraph);

        tdfav = document.createElement('td');
        starimg = new Image();

        tdfav.classList.add('favcell');
        tdfav.setAttribute('favorited', 'false');
        starimg.classList.add('favstar');
        starimg.src = 'static/images/EmptyStar.png';
        tdfav.appendChild(starimg);
        tr.appendChild(tdfav);
        tbdy.appendChild(tr);
    }

    tbl.appendChild(tbdy);
    body.appendChild(tbl);
});

//Create sortable table columns
$(function () {
    $('#coins').tablesorter();
});

//Clickable modal
$(function () {
    var modal = $('#myModal');
    $('.clickable-row').click(function () {
        var coin_name = this.id;
        modal.get(0).setAttribute('coin', coin_name);
        modal.css('display', 'block');
        $('#defaultOpen').get(0).click();
        loadModalData(coin_name, modal);
        loadGraph(coin_name, modal);
    });

    $('.close').click(function () {
        modal.css('display', 'none');
        resetCanvas(modal);
    });

    $(window).click(function (event) {
        if (event.target.id === modal.attr('id')) {
            modal.css('display', 'none');
            resetCanvas(modal);
        }
    });
});