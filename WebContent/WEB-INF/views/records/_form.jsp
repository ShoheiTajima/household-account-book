<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="use_date">取引日</label><br />
<input type="date" name="use_date" value="<fmt:formatDate value='${record.use_date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="name">ユーザーID</label><br />
<c:out value="${sessionScope.login_user.user_id}" />
<br /><br />

<label for="overview">概要</label><br />
<input type="text" name="overview" value="${record.overview}" />
<br /><br />

<label for="tranzaction">取引額</label><br />
<input type="text" name="tranzaction" value="${record.tranzaction}" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>