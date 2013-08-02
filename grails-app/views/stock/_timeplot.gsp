<script type="text/javascript" src="http://api.simile-widgets.org/timeplot/1.1/timeplot-api.js"></script>

<script>
    var timeplot1;

    var color3 = new Timeplot.Color('#91AA9D');
    var color5 = new Timeplot.Color('#193441');
    var red = new Timeplot.Color('#B9121B');
    var blue = new Timeplot.Color('#193441');
    var green = new Timeplot.Color('#468966');
    var lightGreen = new Timeplot.Color('#5C832F');
    var celeste = new Timeplot.Color('#00AAE4');

    var gridColor = new Timeplot.Color('#FFE57F');

    function onLoad() {

        var timeGeometry = new Timeplot.DefaultTimeGeometry({
            gridColor: "#FFFFFF",
            axisLabelsPlacement: "bottom"
        });

        var geometry1 = new Timeplot.DefaultValueGeometry({
            min: 0
        });

        var eventSource1 = new Timeplot.DefaultEventSource();
        var dataSourceStock = new Timeplot.ColumnSource(eventSource1, 1);


        var plotInfo1 = [
            Timeplot.createPlotInfo({
                id: "stock",
                dataSource: dataSourceStock,
                timeGeometry: timeGeometry,
                valueGeometry: geometry1,
                lineColor: blue,
                showValues: true,
                roundValues: false,
                lineWidth: 2
            })
        ];

        timeplot1 = Timeplot.create(document.getElementById("timeplot1"), plotInfo1);
        timeplot1.loadText("/home/antonio/timeplot/T/stock.txt", " ", eventSource1);


        var resizeTimerID = null;

        function onResize() {
            if (resizeTimerID == null) {
                resizeTimerID = window.setTimeout(function () {
                    resizeTimerID = null;
                    if (timeplot1) timeplot1.repaint();
                }, 0);
            }
        }
    }
</script>


<script>
    function codeAddress() {
        onLoad();
    }
    window.onload = codeAddress;
</script>

<div id="content">
    <div id="timeplot1" style="height: 400px" class="timeplot"></div>
</div>

