<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Warsztat 3 - CoderLab</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>SB Admin 2 - Dashboard</title>
    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- Custom styles for this template-->
    <link href="<c:url value="/resources/theme/css/sb-admin-2.css"/>" rel="stylesheet">
</head>

<body id="page-top">
<!-- Page Wrapper -->
<div id="wrapper">
    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href='<c:url value="/admin/"/>'>
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3"> PANEL GŁÓWNY </div>
        </a>
        <!-- Divider -->
        <hr class="sidebar-divider my-0">
        <!-- Nav Item - Dashboard -->
        <li class="nav-item active">
            <a class="nav-link" href='<c:url value="/institution/all"/>'>
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span class="font-size:3em">FUNDACJE</span></a>
            <a class="nav-link" href='<c:url value="/admin/usersAdmin"/>'>
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span class="font-size:3em">ADMINISTRATORZY</span></a>
            <a class="nav-link" href='<c:url value="/admin/usersUser"/>'>
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span class="font-size:3em">UŻYTKOWNICY</span></a>
        </li>
    </ul>
    <!-- End of Sidebar --><!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">
        <!-- Main Content -->
        <div id="content">
            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow"></nav>
            <div class="container-fluid">
            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                <h1 class="text-left h3 mb-0 text-gray-800">Witaj ${userFullName} </h1>
                <form action="<c:url value="/logout"/>" method="post" >
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm text-white-50"
                           type="submit" value="Wyloguj">
                    <a href="http://localhost:8080/" class="d-none d-sm-inline-block btn btn-sm
                     btn-primary shadow-sm text-white-50">Strona główna</a></form>
            </div>
