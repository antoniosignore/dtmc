<html>
<head>
    <meta content="oneblock" name="layout"/>
    <title><g:message code="title.portfolio.list"/></title>
</head>

<body>

<content tag="main1">
    <h1><g:message code="portfolio.list"/></h1>

    <ul class="product-list">
        <g:each in="${portfolioList}" var="portfolio">
            <li>
                <g:link action="view" id="${portfolio.id}">
                    <img src="${resource(dir: 'images', file: 'product-image.jpg')}" alt="" width="100" height="100"
                         class="product-image"/>
                </g:link>
                <span class="product-details">
                    <h2><g:link action="view" id="${portfolio.id}">${portfolio.name}</g:link></h2>

                    <p class="product-description">
                        ${portfolio.description}
                    </p>
                </span>
            </li>
        </g:each>
    </ul>
</content>

<content tag="main2">

    sassolino
</content>
</body>
</html>