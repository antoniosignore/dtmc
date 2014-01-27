<script class="code" language="javascript" type="text/javascript">
    $(document).ready(function () {
        var plot1 = $.jqplot('chart_${indicatorCounter}', [data_${indicatorCounter}], {
            highlighter: {
                show: true
            },
            title: '${indicator.name}',
            axes: {xaxis: {renderer: $.jqplot.DateAxisRenderer},
                yaxis: {yaxis: 'y2axis'}
            },
            grid: {
                borderColor: 'transparent',
                shadow: false,
                drawBorder: false
            },
            series: [
                {lineWidth: 1, showMarker: false}
            ]
        });
    });
    data_${indicatorCounter} = ${indicator.indicator.getJqPlot()}
</script>

<div class="item" id='chart_${indicatorCounter}' style='height:200px; width:100%;'></div>

