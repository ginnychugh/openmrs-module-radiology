<%@ include file="/WEB-INF/template/include.jsp"%>
<c:set var="DO_NOT_INCLUDE_JQUERY" value="true" />
<%@ include file="/WEB-INF/template/header.jsp"%>
<c:set var="INCLUDE_TIME_ADJUSTMENT" value="true" />
<%@ include file="/WEB-INF/view/module/radiology/template/includeScripts.jsp"%>

<style>
  li.foundTemplates {
    color: hotpink;
  }

  li.foundTemplates:hover {
    background: #f8f5ff;
    border-bottom: 1px solid #253c93;
    cursor: pointer;
  }
</style>

<script>
  $j(function() {

      var templateId = "";

      $j('.reportTemplates').hide();

      $j('#reportType').on('change', function() {
        if (this.value === "MRRT") {
            $j('.reportTemplates').show();
        } else {
            $j('.reportTemplates').hide();
        }
      });

      var search = function(name) {

        var url = "/openmrs/ws/rest/v1/mrrtreporttemplate?title=" + name;
        $j.getJSON(url, function(result) {

          var html = "";
          if ($j('#templateName').val() === "") {
              html = "";
          } else {
              html = result.results.map(function(o) {
                  return "<li class='foundTemplates' data-uuid='" + o.uuid + "'>" + o.display + "</li>";
              }).join('');
          }
          $j('.update').html(html);
        });
      }

      $j('#templateName').keyup(function() {
        var name = $j('#templateName').val();
        templateId = "";
        search(name);
      });

      $j('.update').on('click', 'li.foundTemplates', function() {
          var templateTitle = $j(this).text();
          $j('#templateName').val(templateTitle);
          $j('.update').html("");
          templateId = $j(this).data("uuid");
      })

      $j('#claimReportForm').on('submit', function(event) {
        event.preventDefault();
        var url = $j('#claimReportButton').data("url");
        var reportType = $j('#reportType option:selected').val();
        if (reportType === "MRRT") {
            url = url + "&templateId=" + templateId;
        }
        window.location.href = url;
      })
  });
</script>
<openmrs:require
  allPrivileges="Get Care Settings,Get Concepts,Get Encounter Roles,Get Encounters,Get Orders,Get Patients,Get Providers,Get Radiology Orders,Get Users,Get Visit Attribute Types,Get Visit Types,Get Visits,View Orders"
  otherwise="/login.htm" redirect="/module/radiology/radiologyOrder.form" />

<!--  This form shows existing RadiologyOrder/discontinued Order -->

<openmrs:hasPrivilege privilege="View Patients">
  <openmrs:portlet url="patientHeader" id="patientDashboardHeader" patientId="${order.patient.patientId}" parameters="showPatientDashboardLink=true" />
  <br>
</openmrs:hasPrivilege>
<c:choose>
  <c:when test="${not empty radiologyOrder}">
    <!--  Show existing RadiologyOrder -->
    <%@ include file="radiologyOrderDisplaySegment.jsp"%>
    <c:if test="${radiologyOrder.completed}">
      <!--  Show form for radiology report -->
      <openmrs:hasPrivilege hasAll="true" privilege="Add Radiology Reports,Delete Radiology Reports,Edit Radiology Reports,Get Radiology Reports">
        <%@ include file="radiologyReportSegment.jsp"%>
      </openmrs:hasPrivilege>
    </c:if>
    <c:if test="${radiologyOrder.discontinuationAllowed}">
      <!--  Show form to discontinue an active non in progress/completed RadiologyOrder -->
      <openmrs:hasPrivilege hasAll="true" privilege="Delete Radiology Orders,Edit Orders">
        <%@ include file="radiologyOrderDiscontinuationSegment.jsp"%>
      </openmrs:hasPrivilege>
    </c:if>
  </c:when>
  <c:otherwise>
    <!--  Show read-only view of discontinuation Order -->
    <%@ include file="discontinuationOrderDisplaySegment.jsp"%>
  </c:otherwise>
</c:choose>
<%@ include file="/WEB-INF/template/footer.jsp"%>
