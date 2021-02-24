<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <link type="text/css" href="${pageContext.request.contextPath}/styles/home.css" rel="stylesheet">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

    <script type="text/javascript">
        var imageURLs = [
            "${pageContext.request.contextPath}/images/1.jpg"
            , "${pageContext.request.contextPath}/images/2.jpg"
            , "${pageContext.request.contextPath}/images/3.jpg"
            , "${pageContext.request.contextPath}/images/4.jpg"
            , "${pageContext.request.contextPath}/images/5.jpg"
            , "${pageContext.request.contextPath}/images/6.jpg"
            , "${pageContext.request.contextPath}/images/7.jpg"
            , "${pageContext.request.contextPath}/images/8.jpg"
            , "${pageContext.request.contextPath}/images/9.jpg"
            , "${pageContext.request.contextPath}/images/10.jpg"
            , "${pageContext.request.contextPath}/images/11.jpg"
            , "${pageContext.request.contextPath}/images/12.jpg"
        ];
        function getImageTag() {
            var img = '<img src=\"';
            var randomIndex = Math.floor(Math.random() * imageURLs.length);
            img += imageURLs[randomIndex];
            img += '\" alt=\"Item image\"';
            img += '\" class=\"img-i\"/>';
            return img;
        }
    </script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <title>${param.titleName}</title>
</head>