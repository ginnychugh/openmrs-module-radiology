<br>
<div>
  <span class="boxHeader"> <b><spring:message code="radiology.radiologyReportTitle" /></b>
  </span>
  <c:choose>
    <c:when test="${radiologyReportNeedsToBeCreated}">
      <form:form method="post" modelAttribute="radiologyOrder" cssClass="box" id="claimReportForm">
        <tr>
          <td><spring:bind path="orderId">
              <div>
                <span><spring:message code="radiology.radiologyReportType"/></span>
                <select id="reportType">
                  <option value="FREE_TEXT">Free Text Report</option>
                  <option value="MRRT">MRRT Report</option>
                </select><br>
                <div class="reportTemplates">
                  <span><spring:message code="radiology.mrrtReportTemplate.search"/></span>
                  <input type="text" name="name" id="templateName" placeholder="<spring:message code="radiology.mrrtReportTemplate.search.placeholder"/>">
                </div>
                <ul class="update"></ul>
              </div>
              <button id="claimReportButton" data-url="${pageContext.request.contextPath}/module/radiology/radiologyReport.form?orderId=${status.value}">
                <spring:message code="radiology.radiologyReportClaim" />
              </button>
            </spring:bind></td>
        </tr>
      </form:form>
    </c:when>
    <c:otherwise>
      <form:form method="post" modelAttribute="radiologyReport" cssClass="box">
        <tr>
          <td><a
            href="${pageContext.request.contextPath}/module/radiology/radiologyReport.form?reportId=${radiologyReport.id}">
              <c:choose>
                <c:when test="${radiologyReport.status == 'DRAFT'}">
                  <spring:message code="radiology.radiologyReportResume" />
                </c:when>
                <c:otherwise>
                  <spring:message code="radiology.radiologyReportShow" />
                </c:otherwise>
              </c:choose>
          </a></td>
        </tr>
      </form:form>
    </c:otherwise>
  </c:choose>
</div>
