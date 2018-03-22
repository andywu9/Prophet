Number.prototype.formatMoney = function(c, d, t){
    var n = this, 
    c = isNaN(c = Math.abs(c)) ? 2 : c, 
    d = d == undefined ? "." : d, 
    t = t == undefined ? "," : t, 
    s = n < 0 ? "-" : "", 
    i = String(parseInt(n = Math.abs(Number(n) || 0).toFixed(c))), 
    j = (j = i.length) > 3 ? j % 3 : 0;
   return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
 };

$(document).ready(function() {

    var body = document.getElementsByClassName('table-container')[0];
    var tbl = document.createElement('table');
    var coin_data = JSON.parse(coin_table);

    tbl.classList.add("table");
    tbl.classList.add("table-hover");
    // tbl.classList.add("table-bordered");
    tbl.classList.add("table-striped");

    tbl.style.width = '100%';
    var tbdy = document.createElement('tbody');

    var trh = document.createElement('tr');
    var th1 = document.createElement('th');
    var th2 = document.createElement('th');
    var th3 = document.createElement('th');
    var th4 = document.createElement('th');
    var th5 = document.createElement('th');
    var th6 = document.createElement('th');
    var th7 = document.createElement('th');

    trh.setAttribute('id', 'header-row')

    th1.appendChild(document.createTextNode('Coin'));
    th2.appendChild(document.createTextNode('Current Price'));
    th3.appendChild(document.createTextNode('Market Cap'));
    th4.appendChild(document.createTextNode('Volume (24h)'));
    th5.appendChild(document.createTextNode('Price Change (24h)'));
    th6.appendChild(document.createTextNode('Graph'));

    trh.appendChild(th1);
    trh.appendChild(th2);
    trh.appendChild(th3);
    trh.appendChild(th4);
    trh.appendChild(th5);
    trh.appendChild(th6);
    trh.appendChild(th7);
    tbdy.appendChild(trh);

    data_type = ['name', 'price', 'market_cap', 'volume', 'circulating_supply'];

    for (var i = 0; i < 10; i++) {
        var tr = document.createElement('tr');

        for (var j = 0; j < data_type.length; j++) {
                var td = document.createElement('td');
                var data = coin_data[i]['fields'][data_type[j]];

                switch(data_type[j]) {
                    case 'price':
                        data = parseFloat(data);
                        data = '$ ' + data.formatMoney(2);
                        break;
                    case 'market_cap':
                    case 'volume':
                        data = parseFloat(data);
                        data = '$ ' + data.formatMoney(0);
                        break;
                }
                var cell_div = document.createElement('div');
                cell_div.classList.add('nowrap');
                cell_div.appendChild(document.createTextNode(data))

                td.appendChild(cell_div);
                tr.appendChild(td);
        } 

        var tdgraph = document.createElement('td');
        graph = document.createElement('canvas');
        graph.classList.add('graph');
        graph.setAttribute('width', '300');
        graph.setAttribute('height', '100');

        tdgraph.appendChild(graph);
        tr.appendChild(tdgraph);

        var tdfav = document.createElement('td');
        var starimg = new Image();

        //TODO: change favorited properies to match user data
        tdfav.classList.add("favcell");
        tdfav.setAttribute("favorited", "false")
        starimg.classList.add('favstar')
        starimg.src = "static/images/EmptyStar.png";
        tdfav.appendChild(starimg);
        tr.appendChild(tdfav);
        tbdy.appendChild(tr);
    }
    tbl.appendChild(tbdy);
    body.appendChild(tbl);
});

$(function() {
    $(".graph").each(function() {
        console.log(this.nodeName);
        var ctx = $(this)[0].getContext('2d');
        var chart = new Chart(ctx, {
            // The type of chart we want to create
            type: 'line',

            // The data for our dataset
            data: {
                labels: ['','','','','','',''],//["January", "February", "March", "April", "May", "June", "July"],
                datasets: [{
                    label: "My First dataset",
                    backgroundColor: 'rgb(255, 99, 132)',
                    borderColor: 'rgb(255, 99, 132)',
                    data: [0, 10, 5, 2, 20, 30, 45],
                }]
            },

            // Configuration options go here
            options: {
                scales: {
                    xAxes: [{
                        display: false
                    }],
                    yAxes: [{
                        display: false
                    }]
                },
                hover: {
                    intersect: false
                },
                tooltips: {
                    mode: 'index',
                }
            }
        });

    }); 
});

$(function() {
    $(".favstar").hover(
    function() {
        $(this).attr("src", "static/images/GoldStar.png"); 
    }, function() {
        $(this).attr("src", "static/images/EmptyStar.png");
    });    
});
