<%@ page import="com.dtmc.security.UserBean" %>
<!doctype html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="kickstart"/>
    <g:set var="entityName" value="${message(code: 'userBean.label', default: 'UserBean')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<section id="show-userBean" class="first">

    <table class="table">
        <tbody>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userBean.fname.label"
                                                     default="Fname"/></td>

            <td valign="top" class="value">${fieldValue(bean: userBeanInstance, field: "fname")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userBean.lname.label"
                                                     default="Lname"/></td>

            <td valign="top" class="value">${fieldValue(bean: userBeanInstance, field: "lname")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userBean.email.label"
                                                     default="Email"/></td>

            <td valign="top" class="value">${fieldValue(bean: userBeanInstance, field: "email")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userBean.phone.label"
                                                     default="Phone"/></td>

            <td valign="top" class="value">${fieldValue(bean: userBeanInstance, field: "phone")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userBean.mobile.label"
                                                     default="Mobile"/></td>

            <td valign="top" class="value">${fieldValue(bean: userBeanInstance, field: "mobile")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userBean.city.label"
                                                     default="City"/></td>

            <td valign="top" class="value">${fieldValue(bean: userBeanInstance, field: "city")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userBean.state.label"
                                                     default="State"/></td>

            <td valign="top" class="value">${fieldValue(bean: userBeanInstance, field: "state")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userBean.country.label"
                                                     default="Country"/></td>

            <td valign="top" class="value">${fieldValue(bean: userBeanInstance, field: "country")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userBean.address1.label"
                                                     default="Address1"/></td>

            <td valign="top" class="value">${fieldValue(bean: userBeanInstance, field: "address1")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userBean.username.label"
                                                     default="Username"/></td>

            <td valign="top" class="value">${fieldValue(bean: userBeanInstance, field: "username")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userBean.password.label"
                                                     default="Password"/></td>

            <td valign="top" class="value">${fieldValue(bean: userBeanInstance, field: "password")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userBean.accountExpired.label"
                                                     default="Account Expired"/></td>

            <td valign="top" class="value"><g:formatBoolean boolean="${userBeanInstance?.accountExpired}"/></td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userBean.accountLocked.label"
                                                     default="Account Locked"/></td>

            <td valign="top" class="value"><g:formatBoolean boolean="${userBeanInstance?.accountLocked}"/></td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userBean.address2.label"
                                                     default="Address2"/></td>

            <td valign="top" class="value">${fieldValue(bean: userBeanInstance, field: "address2")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userBean.company.label"
                                                     default="Company"/></td>

            <td valign="top" class="value">${fieldValue(bean: userBeanInstance, field: "company")}</td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userBean.enabled.label"
                                                     default="Enabled"/></td>

            <td valign="top" class="value"><g:formatBoolean boolean="${userBeanInstance?.enabled}"/></td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userBean.lastLoginDate.label"
                                                     default="Last Login Date"/></td>

            <td valign="top" class="value"><g:formatDate date="${userBeanInstance?.lastLoginDate}"/></td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userBean.passwordExpired.label"
                                                     default="Password Expired"/></td>

            <td valign="top" class="value"><g:formatBoolean boolean="${userBeanInstance?.passwordExpired}"/></td>

        </tr>

        <tr class="prop">
            <td valign="top" class="name"><g:message code="userBean.registerDate.label"
                                                     default="Register Date"/></td>

            <td valign="top" class="value"><g:formatDate date="${userBeanInstance?.registerDate}"/></td>

        </tr>

        </tbody>
    </table>
</section>

</body>

</html>
