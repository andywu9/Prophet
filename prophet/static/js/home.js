"use strict";
/*jslint browser: true, continue:true */
/*global Chart, $, jQuery, alert */
/*global coin_table, createMainTableGraph, createFavoriteCell, resetCanvas, loadModalData, loadGraph */


$(document).ready(function () {
    /*
        This function creates the main page data table, including creating HTML elements,
        populating the elements with data, and creating and populating the graph. This does too much, separate
        the code better. 
    */

    //Retrieve database data
    var coin_data = JSON.parse(coin_table),
        data_type = ['pk', 'price', 'market_cap', 'volume', 'price_change_day'],
        tbdy = document.createElement('tbody'),
        thead = document.createElement('thead'),
        trh = document.createElement('tr'),
        header_titles = ['Coin', 'Current Price', 'Market Cap', 'Volume (24h)', 'Price Change (24h)', 'Price History'],//, 'Favorite'],
        body = document.getElementsByClassName('table-container')[0],
        tbl = document.createElement('table'),
        tr,
        th,
        td,
        coin_name,
        cell_div,
        data,
        graph,
        tdgraph,
        ctx,
        i,
        j;


    //Add table classes
    tbl.classList.add('table');
    tbl.classList.add('table-hover');
    tbl.classList.add('table-striped');
    tbl.classList.add('table-sorter');
    tbl.setAttribute('id', 'coins');

    //Create header row
    trh.setAttribute('id', 'header-row');

    //Create header cells
    for (i = 0; i < header_titles.length; i += 1) {
        th = document.createElement('th');
        if (header_titles[i] === 'Price History' || header_titles[i] === 'Favorite') {
            th.setAttribute('data-sorter', false);
            th.classList.add('no-sorter');
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

        //Create Graph for current coin
        ctx = graph.getContext('2d');
        createMainTableGraph(coin_name, ctx);

        //Add graph to cell and cell to row
        tdgraph.appendChild(graph);
        tr.appendChild(tdgraph);

        //Add favorite star to row
        //tr.appendChild(createFavoriteCell());

        //Add row to table
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
        resetCanvas(modal);
        loadModalData(coin_name);
        loadGraph(coin_name, modal);
    });

    $('.close').click(function () {
        modal.css('display', 'none');
        resetCanvas(modal);
        $('#data-tab-button').click();
    });

    $(window).click(function (event) {
        if (event.target.id === modal.attr('id')) {
            modal.css('display', 'none');
            resetCanvas(modal);
            $('#data-tab-button').click();
        }
    });
});

//Add favorite buttons
$(function () {
    $(".favstar").hover(function () {
        $(this).attr('src', 'static/images/GoldStar.png');
    }, function () {
        $(this).attr('src', 'static/images/EmptyStar.png');
    });
});