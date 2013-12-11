
    <script class="code" language="javascript" type="text/javascript">
        $(document).ready(function () {
            var plot1 = $.jqplot('chart_${indicatorCounter}', [data_${indicatorCounter}], {
                title: '${indicator.name}',
                axes: {xaxis: {renderer: $.jqplot.DateAxisRenderer}},
                series: [
                    {showMarker: false}
                ]
            });
        });
        data_${indicatorCounter} =
        ${indicator.indicator.getJqPlot()}
    </script>

    <div class="item" id='chart_${indicatorCounter}' style='height:200px; width:200px;'></div>

