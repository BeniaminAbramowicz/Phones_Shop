<nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
  <button class="navbar-toggler" data-toggle="collapse" data-target="#collapse_target">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapse_target">
    <ul class="navbar-inner navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/index">Home Page <span class="sr-only"></span></a>
      </li>
      <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/products">Products <span class="sr-only"></span></a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Phones
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Samsung</a>
          <a class="dropdown-item" href="#">Xiaomi</a>
          <a class="dropdown-item" href="#">LG</a>
          <a class="dropdown-item" href="#">Apple</a>
          <a class="dropdown-item" href="#">One Plus</a>
          <a class="dropdown-item" href="#">Nokia</a>
          <a class="dropdown-item" href="#">MyPhone</a>
        </div>
      </li>
      <li class="nav-item">
         <a class="nav-link" href="#">Accessories</a>
      </li>
      <li class="nav-item">
         <a class="nav-link" href="#">Login</a>
      </li>
    </ul>
  </div>
</nav>