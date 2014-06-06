<%
    ui.decorateWith("kenyaui", "panel", [heading: "Child Services Care"])

    def dataPoints = []



%>
<div class="ke-stack-item">
    <% dataPoints.each { print ui.includeFragment("kenyaui", "widget/dataPoint", it) } %>
</div>