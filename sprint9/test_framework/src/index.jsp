<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sprint1</title>
</head>
<body>
    <style>
        form{
            display:flex ;
            flex-direction:column;
        }
        form input{
            width:150px;
        }
    </style>
    <form action="run" method="post" enctype="multipart/form-data">
        <input type="text" name="Nom">
        <input type="text" name="Prenom">
        <input type="date" name="Dates">
        <input type="number" name="Age">
        <input type="file" name="File">
        <input type="submit" value="valider">
    </form>
</body>