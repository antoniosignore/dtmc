
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


    <div class="container-fluid well span12">

        <div class="row-fluid">

            <div class="item" id='chart_${indicatorCounter}' style='height:200px; width:400px;'></div>

            <div class="span4">
                <h3>User Name</h3>
                <h6>Email: MyEmail@servidor.com</h6>
                <h6>Ubication: Colombia</h6>
                <h6>Old: 1 Year</h6>
                <h6><a href="#">More...</a></h6>
            </div>

            <div class="span2">
                <div class="btn-group">
                    <a class="btn dropdown-toggle btn-info" data-toggle="dropdown" href="#">
                        Action
                        <span class="icon-cog icon-white"></span><span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#"><span class="icon-wrench"></span> Modify</a></li>
                        <li><a href="#"><span class="icon-trash"></span> Delete</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>


    %{--<div class="item" id='chart_${indicatorCounter}' style='height:200px; width:200px;'></div>--}%

