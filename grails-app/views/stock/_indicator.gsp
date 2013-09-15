<div class="statusMessage" id="message_${indicatorCounter}">
    <strong>
        <span class="author">${indicator.name}
        </span>
    </strong>

    <div id="chart_${getFullname()}" style="height:300px; width:650px;"></div>
    <g:javascript>
        $(document).ready(function(){
        var line1=[data_${getFullname()}];
        var plot1 = $.jqplot('chart_${getFullname()}', [line1], {
        title:'Default Date Axis',
        axes:{xaxis:{renderer:$.jqplot.DateAxisRenderer}},
        series:[{lineWidth:4, markerOptions:{style:'square'}}]
        });
        });
    </g:javascript>

</div>
