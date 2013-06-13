<html>
<head>
    <meta content="twoblocks" name="layout"/>
    <title>${portfolio.name}</title>
</head>

<body>
<content tag="left1">
    <h1>${portfolio.name}</h1>

    <p class="product-body">
        <img src="${resource(dir: 'images', file: 'product-image.jpg')}" alt="" width="220" height="220"
             class="product-image"/>

        ${portfolio.description}
    </p>
</content>

<content tag="right1">
    <div id="list-products">
        <h2><g:message code="products.list.newest"/></h2>
        <ul>
            <g:each in="${portfolioList}" var="product">
                <li>
                    <p>
                        <g:link controller="portfolioList" action="myview"
                                id="${portfolio.id}">${portfolio.name}</g:link>
                    </p>
                </li>
            </g:each>
        </ul>
    </div>
</content>
</body>
</html>