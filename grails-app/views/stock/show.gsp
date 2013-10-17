<%@ page import="com.netnumeri.server.utils.StockUtils; com.netnumeri.server.finance.finpojo.asset.Stock" %>
<!doctype html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName" value="${message(code: 'stock.label', default: 'Stock')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>

    %{--<r:external uri="/css/metro-bootstrap.css"/>--}%

    <r:external uri="/css/jquery.jqplot.css"/>
    <r:external uri="/js/jquery.jqplot.js"/>
    <r:external uri="/js/plugins/jqplot.dateAxisRenderer.min.js"/>
    <r:external uri="/js/plugins/jqplot.ohlcRenderer.min.js"/>
    <r:external uri="/js/plugins/jqplot.highlighter.min.js"/>

</head>

<body>

<section id="show-stock" class="first">

    <table class="table table-striped table-bordered table-condensed table-hover">
        <tbody>
        <tr class="prop">
            <td valign="top" class="name"><g:message code="stock.name.label" default="Name"/></td>
            <td valign="top" class="value">${fieldValue(bean: stockInstance, field: "name")}</td>
        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="stock.description.label" default="Description"/></td>
            <td valign="top" class="value">${fieldValue(bean: stockInstance, field: "description")}</td>

        </tr>
        </tbody>
    </table>

    %{--<!-- Main Area -->--}%
    %{--<div id="main_area">--}%
    %{--<!-- Slider -->--}%
    %{--<div class="row">--}%
    %{--<div class="span12" id="slider">--}%
    %{--<!-- Top part of the slider -->--}%
    %{--<div class="row">--}%
    %{--<div class="span8" id="carousel-bounding-box">--}%
    %{--<div id="myCarousel" class="carousel slide">--}%
    %{--<!-- Carousel items -->--}%
    %{--<div class="carousel-inner">--}%
    %{--<div class="active item" data-slide-number="0">--}%
    %{--<g:render template="candlestick"/>--}%
    %{--</div>--}%
    %{--<div class="item" data-slide-number="1">--}%
    %{--<g:render template="candlestick"/>--}%
    %{--</div>--}%
    %{--<div class="item" data-slide-number="2">--}%
    %{--<g:render template="snapshot"/>--}%
    %{--</div>--}%
    %{--</div>--}%
    %{--<!-- Carousel nav -->--}%
    %{--<a class="carousel-control left" href="#myCarousel" data-slide="prev">‹</a>--}%
    %{--<a class="carousel-control right" href="#myCarousel" data-slide="next">›</a>--}%
    %{--</div>--}%
    %{--</div>--}%
    %{--<div class="span4" id="carousel-text"></div>--}%

    %{--<div style="display: none;" id="slide-content">--}%
    %{--<div id="slide-content-0">--}%
    %{--<h2>Slider One</h2>--}%
    %{--<p>Lorem Ipsum Dolor</p>--}%
    %{--<p class="sub-text">October 24 2012 - <a href="#">Read more</a></p>--}%
    %{--</div>--}%
    %{--<div id="slide-content-1">--}%
    %{--<h2>Slider Two</h2>--}%
    %{--<p>Lorem Ipsum Dolor</p>--}%
    %{--<p class="sub-text">October 24 2012 - <a href="#">Read more</a></p>--}%
    %{--</div>--}%
    %{--<div id="slide-content-2">--}%
    %{--<h2>Slider Three</h2>--}%
    %{--<p>Lorem Ipsum Dolor</p>--}%
    %{--<p class="sub-text">October 24 2012 - <a href="#">Read more</a></p>--}%
    %{--</div>--}%
    %{--</div>--}%
    %{--</div>--}%

    %{--</div>--}%
    %{--</div> <!--/Slider-->--}%

    %{--<div class="row hidden-phone" id="slider-thumbs">--}%
    %{--<div class="span12">--}%
    %{--<!-- Bottom switcher of slider -->--}%
    %{--<ul class="thumbnails">--}%
    %{--<li class="span2">--}%
    %{--<a class="thumbnail" id="carousel-selector-0">--}%
    %{--<img src="http://placehold.it/170x100&text=one" />--}%
    %{--</a>--}%
    %{--</li>--}%
    %{--<li class="span2">--}%
    %{--<a class="thumbnail" id="carousel-selector-1">--}%
    %{--<img src="http://placehold.it/170x100&text=two" />--}%
    %{--</a>--}%
    %{--</li>--}%
    %{--<li class="span2">--}%
    %{--<a class="thumbnail" id="carousel-selector-2">--}%
    %{--<img src="http://placehold.it/170x100&text=three" />--}%
    %{--</a>--}%
    %{--</li>--}%
    %{--</ul>--}%
    %{--</div>--}%
    %{--</div>--}%
    %{--</div>--}%
    %{--<script>--}%
    %{--jQuery(document).ready(function($) {--}%

    %{--$('#myCarousel').carousel({--}%
    %{--interval: 5000--}%
    %{--});--}%

    %{--$('#carousel-text').html($('#slide-content-0').html());--}%

    %{--//Handles the carousel thumbnails--}%
    %{--$('[id^=carousel-selector-]').click( function(){--}%
    %{--var id_selector = $(this).attr("id");--}%
    %{--var id = id_selector.substr(id_selector.length -1);--}%
    %{--var id = parseInt(id);--}%
    %{--$('#myCarousel').carousel(id);--}%
    %{--});--}%

    %{--// When the carousel slides, auto update the text--}%
    %{--$('#myCarousel').on('slid', function (e) {--}%
    %{--var id = $('.item.active').data('slide-number');--}%
    %{--$('#carousel-text').html($('#slide-content-'+id).html());--}%
    %{--});--}%
    %{--});--}%
    %{--</script>--}%

    <g:render template="snapshot"/>
    <g:render template="candlestick"/>
    <div id="messages">
        <dtmc:renderIndicators indicators="${indicators}"/>
    </div>

</section>

</body>

</html>
