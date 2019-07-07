<nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
  <button class="navbar-toggler" data-toggle="collapse" data-target="#collapse_target">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapse_target">
    <ul class="navbar-inner navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/index">Home Page <span class="sr-only"></span></a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Phones
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="${pageContext.request.contextPath}/samsung">Samsung</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/xiaomi">Xiaomi</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/lg">LG</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/apple">Apple</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/oneplus">One Plus</a>
        </div>
      </li>
      <li class="nav-item">
         <a class="nav-link" href="${pageContext.request.contextPath}/accessories">Accessories</a>
      </li>
      <li class="nav-item">
         <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
      </li>
    </ul>
  </div>
</nav>