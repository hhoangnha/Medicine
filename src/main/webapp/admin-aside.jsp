
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib  prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<% String isCollapsed = "collapsed";%>
<!-- ======= Sidebar ======= -->
<aside id="sidebar" class="sidebar">

    <!-- End Tables Nav -->

    <ul class="sidebar-nav" id="sidebar-nav">
        <c:if test="${sessionScope.IsAdmin==1}">
            <li class="nav-item ">
                <a class="nav-link <%= request.getRequestURI().endsWith("/AdminController") ? isCollapsed : ""%> " href="/AdminController">
                    <i class="bi bi-grid"></i>
                    <span class="">Report</span>
                </a>
            </li>
        </c:if>
        <li class="nav-item  ">
            <a class="nav-link  collapsed" data-bs-target="#tables-nav" data-bs-toggle="collapse" href="#">
                <i class="bi bi-layout-text-window-reverse"></i><span>Users</span><i class="bi bi-chevron-down ms-auto"></i>
            </a>
            <ul id="tables-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
                <li>
                    <a href="/AdminController/Users" >
                        <i class="bi bi-circle"></i><span>User Manager</span>
                    </a>
                </li>
                <c:if test="${sessionScope.IsAdmin==1}">
                    <li>
                        <a href="/ManageStaffController" >
                            <i class="bi bi-circle"></i><span>Staff Manager</span>
                        </a>
                    </li>


                    <li>
                        <a href="/AdminController/RestoreUsers">
                            <i class="bi bi-circle"></i><span>Restore Manager</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </li><!-- End Tables Nav -->

        <li class="nav-item">
            <a
                class="nav-link collapsed"
                data-bs-target="#charts-nav"
                data-bs-toggle="collapse"
                href="#"
                >
                <i class="bi bi-bar-chart"></i><span>Categories</span
                ><i class="bi bi-chevron-down ms-auto"></i>
            </a>
            <ul
                id="charts-nav"
                class="nav-content collapse"
                data-bs-parent="#sidebar-nav"
                >
                <li>
                    <a href="/AdminCateController">
                        <i class="bi bi-circle"></i><span>Categories Manager</span>
                    </a>
                </li>
                <li>
                    <a href="/AdminCateController/RestoreCate">
                        <i class="bi bi-circle"></i><span>Categories Restore</span>
                    </a>
                </li>
            </ul>
        </li>
        <!-- End Charts Nav -->

        <li class="nav-item">
            <a
                class="nav-link collapsed"
                data-bs-target="#icons-nav"
                data-bs-toggle="collapse"
                href="#"
                >
                <i class="bi bi-gem"></i><span>Products</span
                ><i class="bi bi-chevron-down ms-auto"></i>
            </a>
            <ul
                id="icons-nav"
                class="nav-content collapse"
                data-bs-parent="#sidebar-nav"
                >
                <li>
                    <a href="/ProductController">
                        <i class="bi bi-circle"></i><span>Products Manager</span>
                    </a>
                </li>
                <li>
                    <a href="/ProductController/RestoreProduct" >
                        <i class="bi bi-circle"></i><span>Products Restore</span>
                    </a>
                </li>
            </ul>
        </li>
        <!-- End Icons Nav -->

        <li class="nav-item">
            <a
                class="nav-link collapsed"
                data-bs-target="#icons-nav2"
                data-bs-toggle="collapse"
                href="#"
                >
                <i class="bi bi-pen"></i><span>Brand</span
                ><i class="bi bi-chevron-down ms-auto"></i>
            </a>
            <ul
                id="icons-nav2"
                class="nav-content collapse"
                data-bs-parent="#sidebar-nav"
                >
                <li>
                    <a href="/BrandController">
                        <i class="bi bi-circle"></i><span>Brand Manager</span>
                    </a>
                </li>
            </ul>
        </li>

        <li class="nav-item">
            <a
                class="nav-link collapsed"
                data-bs-target="#icons-nav3"
                data-bs-toggle="collapse"
                href="#"
                >
                <i class="bi bi-box"></i><span>Order</span
                ><i class="bi bi-chevron-down ms-auto"></i>
            </a>
            <ul
                id="icons-nav3"
                class="nav-content collapse"
                data-bs-parent="#sidebar-nav"
                >
                <li>
                    <a href="/OrderID_ad">
                        <i class="bi bi-circle"></i><span>Order Manager</span>
                    </a>
                </li>
            </ul>
        </li>
        <li class="nav-item">
            <a
                class="nav-link collapsed"
                data-bs-target="#icons-nav4"
                data-bs-toggle="collapse"
                href="#"
                >
                <i class="bi bi-crosshair"></i><span>Unit</span
                ><i class="bi bi-chevron-down ms-auto"></i>
            </a>
            <ul
                id="icons-nav4"
                class="nav-content collapse"
                data-bs-parent="#sidebar-nav"
                >
                <li>
                    <a href="/UnitController">
                        <i class="bi bi-circle"></i><span>Unit Manager</span>
                    </a>
                </li>
                <li>
                    <a href="/UnitController/RestoreUnit">
                        <i class="bi bi-circle"></i><span>Unit Restore</span>
                    </a>
                </li>
            </ul>

            <!-- vinh -->
        </li>



        <c:if test="${sessionScope.IsAdmin==1}">
            <li class="nav-item">
                <a class="nav-link collapsed" href="/ManufacturerController">
                    <i class="bi bi-grid"></i>
                    <span>Manufacturer</span>
                </a>
            </li>
        </c:if>


</aside>
<!-- End Sidebar-->
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const reportNavLink = document.querySelector('.nav-item > .nav-link');

        document.querySelectorAll('.sidebar-nav a.nav-link').forEach(link => {
            link.addEventListener('click', function () {
                if (!reportNavLink.classList.contains('collapsed')) {
                    reportNavLink.classList.add('collapsed');
                }
            });
        });
    });
</script>

