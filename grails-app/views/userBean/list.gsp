<%@ page import="com.dtmc.security.UserBean" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName" value="${message(code: 'userBean.label', default: 'UserBean')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>

<section id="list-userBean" class="first">

    <table class="table table-bordered">
        <thead>
        <tr>

            <g:sortableColumn property="fname"
                              title="${message(code: 'userBean.fname.label', default: 'Fname')}"/>

            <g:sortableColumn property="lname"
                              title="${message(code: 'userBean.lname.label', default: 'Lname')}"/>

            <g:sortableColumn property="email"
                              title="${message(code: 'userBean.email.label', default: 'Email')}"/>

            <g:sortableColumn property="phone"
                              title="${message(code: 'userBean.phone.label', default: 'Phone')}"/>

            <g:sortableColumn property="mobile"
                              title="${message(code: 'userBean.mobile.label', default: 'Mobile')}"/>

            <g:sortableColumn property="city"
                              title="${message(code: 'userBean.city.label', default: 'City')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${userBeanInstanceList}" status="i" var="userBeanInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                <td><g:link action="show"
                            id="${userBeanInstance.id}">${fieldValue(bean: userBeanInstance, field: "fname")}</g:link></td>

                <td>${fieldValue(bean: userBeanInstance, field: "lname")}</td>

                <td>${fieldValue(bean: userBeanInstance, field: "email")}</td>

                <td>${fieldValue(bean: userBeanInstance, field: "phone")}</td>

                <td>${fieldValue(bean: userBeanInstance, field: "mobile")}</td>

                <td>${fieldValue(bean: userBeanInstance, field: "city")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <bs:paginate total="${userBeanInstanceTotal}"/>
    </div>
</section>

</body>

</html>
