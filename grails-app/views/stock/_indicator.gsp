<div class="statusMessage" id="message_${indicatorCounter}">

    <script class="code" language="javascript" type="text/javascript">
        $(document).ready(function () {
            var plot1 = $.jqplot('chart_${indicator.name}', [data_${indicator.name}], {
                title: '${indicator.name}',
                axes: {xaxis: {renderer: $.jqplot.DateAxisRenderer}},
                series: [
                    {showMarker: false}
                ]
            });
        });
        data_${indicator.name} =
        ${indicator.indicator.getJqPlot()}
    </script>

    <div id='chart_${indicator.name}' style='height:200px; width:400px;'></div>

</div>
