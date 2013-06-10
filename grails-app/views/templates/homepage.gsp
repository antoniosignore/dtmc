<%-- File: grails-app/views/templates/homepage.gsp --%>
<html>
<head>
    <meta name="layout" content="fiveblocks"/>
    <title><g:message code="title.homepage"/></title>
</head>

<body>

<content tag="banner">
    <h1>Welcome to Grails Layout Demo</h1>
</content>

<content tag="left1">
    <p>left1</p>
</content>

<content tag="left2">
    <p>left2</p>
</content>

<content tag="box-left">
    <p>box left</p>
</content>

<content tag="box-right">
    <p>box right</p>
</content>

<content tag="right1">
    <div id="list-products">
        <h2><g:message code="products.list.newest"/></h2>
        <ul>
            <g:each in="${portfolioList}" var="product">
                <li>
                    <g:link controller="portfolio" action="myview" id="${product.id}">${product.name}</g:link>
                </li>
            </g:each>
        </ul>
    </div>
</content>
</body>
</html>