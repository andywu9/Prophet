"use strict";
/*jslint browser: true, continue:true */
/*global $, jQuery, Chart, historical_table, prediction_table, data, symbols, descriptions */

/**
  * loadGraph loads a new chartjs graph into provided modal
  *
  * Input:
  *     coin_name - the name of the coin whose graph needs to be added
  *     modal - the modal to add the new graph to
  *     date_restrict - the date of the oldest data to include in the graph
  *
  * Output: (none)
  **/
var loadGraph = function (coin_name, modal, date_restrict) {
    var historical_data = JSON.parse(historical_table),
        prediction_data = JSON.parse(prediction_table),
        historical_date,
        point,
        graph_data = [],
        future_data = [],
        time_labels = [],
        data,
        canvas,
        ctx,
        chart;

    // Create data set for historical data
    for (data in historical_data[coin_name]) {
        if (historical_data[coin_name].hasOwnProperty(data)) {

            // Skip data that from before desired date
            historical_date = new Date(historical_data[coin_name][data].datetime);
            if (date_restrict === undefined || historical_date > date_restrict) {
                point = {
                    x : new Date(historical_data[coin_name][data].datetime),
                    y : historical_data[coin_name][data].historical_price,
                };

                graph_data.push(point);
                time_labels.push(historical_data[coin_name][data].datetime);
            }
        }
    }

    // Create data set for predicted data.
    for (data in prediction_data[coin_name]) {
        if (prediction_data[coin_name].hasOwnProperty(data)) {

            point = {
                x : prediction_data[coin_name][data].datetime,
                y : prediction_data[coin_name][data].predicted_price,
            };

            future_data.push(point);
            time_labels.push(prediction_data[coin_name][data].datetime);
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
                label: "Historical Price",
                radius: 0,
                fill: false,
                borderColor: 'rgb(255, 99, 132)',
                data: graph_data,
            },
                {
                    label: "Predicted Price",
                    radius: 0,
                    fill: false,
                    borderColor: 'rgb(0, 0, 255)',
                    data: future_data,
                }]
        },

        // Configuration options go here
        options: {
            responsive: true,
            legend: {
                display: true,
                position: "bottom",
            },
            scales: {
                xAxes: [{
                    type: 'time',
                    ticks: {
                        source: 'auto',
                        autoSkip: true,
                    },
                }],
            },
            hover: {
                mode: 'nearest',
                intersect: false
            },
            tooltips: {
                mode: 'nearest',
                intersect: false,
            },
        }
    });
};

/**
  * resetCanvas removes and replaces the canvas in a modal,
  *         to make room for a new graph to be added.
  *
  * Input:
  *     modal - the modal to remove and replace the canvas from
  *
  * Output: (none)
  **/
var resetCanvas = function (modal) {
    var canvas;

    // Remove the canvas and iframes
    modal.find('canvas').remove();
    modal.find('iframe').remove();

    //Create new canvas
    canvas = document.createElement('canvas');

    //Add new canvas to modal
    modal.find('#modal-graph-cell').get(0).appendChild(canvas);
};

/**
  * openHistory changes the active history tab in the modal, updates the graph
  *
  * Input:
  *     evt - the event that holds the data for which tab was clicked
  *     days_back - how far back the data will be restricted, based on the tab that was clicked
  *
  * Output: (none)
  **/
var openHistory = function (evt, days_back) {
    var date_restrict = new Date(),
        modal = $('#myModal'),
        tablinks = $(".tablinks"),
        i;

    // Conver the days back into milliseconds. Makes the math easy with getTime for dates.
    date_restrict = new Date(date_restrict.getTime() - days_back * 24 * 60 * 60 * 1000);

    // Remove the current graph and load the new graph
    resetCanvas(modal);
    loadGraph(modal.get(0).getAttribute('coin'), modal, date_restrict);

    // Deactivate all tabs
    for (i = 0; i < tablinks.length; i += 1) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Active current active tab
    evt.currentTarget.className += " active";
};

/**
  * loadModalData loads price change and volume change data into the modal.
  *         The data is separated into 1, 6, 12, and 24 hour points.
  *
  * Input:
  *     coin_name - the name of the coin to load the data for
  *
  * Output: (none)
  **/
var loadModalData = function (coin_name) {
    var title = $('h3').get(0),
        change_data,
        symbol,
        description,
        desc;

    // Set all data
    title.innerText = coin_name;
    change_data = JSON.parse(data)[coin_name];
    $('#price_change_24').get(0).innerText = change_data[0] === 'NA' ? change_data[0] : change_data[0] + '%';
    $('#price_change_1').get(0).innerText = change_data[1] === 'NA' ? change_data[1] : change_data[1] + '%';
    $('#volume_change_1').get(0).innerText = change_data[2] === 'NA' ? change_data[2] : '$' + parseFloat(change_data[2]).formatMoney(2);
    $('#volume_change_6').get(0).innerText = change_data[3]  === 'NA' ? change_data[3] : '$' + parseFloat(change_data[3]).formatMoney(2);
    $('#volume_change_12').get(0).innerText = change_data[5]  === 'NA' ? change_data[5] : '$' + parseFloat(change_data[5]).formatMoney(2);
    $('#volume_change_24').get(0).innerText = change_data[7]  === 'NA' ? change_data[7] : '$' + parseFloat(change_data[7]).formatMoney(2);
    $('#price_change_6').get(0).innerText = change_data[4] === 'NA' ? change_data[4] : change_data[4] + '%';
    $('#price_change_12').get(0).innerText = change_data[6] === 'NA' ? change_data[6] : change_data[6] + '%';

    // Retrieve the coin's description for the information tab
    symbol = JSON.parse(symbols)[coin_name];
    description = JSON.parse(descriptions)[symbol];
    desc = $('#coin-desc-text').get(0);
    desc.innerText = description;
};