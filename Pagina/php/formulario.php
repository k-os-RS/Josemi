<!DOCTYPE html>
<html>
<html>
    <head>
        <meta charset="utf-8">
    </head>
<?php
$link = mysqli_connect("localhost", "root","");
mysqli_select_db($link,"formulario");
//variables
$Nombre = $_POST["Nombre"];
$Email = $_POST["Email"];
$Telefono = $_POST["Telefono"];
$Grado_Medio = $_POST["Grado_Medio"];
$Grado_superior = $_POST["Grado_superior"];
$privacidad = $_POST["privacidad"];
$result=mysqli_query($link,"select*from formulario"); 
$F=true;
//codigo
$bug="INSERT INTO alumno VALUES ('$Nombre','$Email','$Telefono')";
if($privacidad != "no"){
    if($Nombre=="" && $Email=="" && $Telefono==""){
        echo "Escriba todos los datos<br> <a href='Formulario.html'>Volver</a>";
    }else if($Nombre == ""){
        echo "Escriba el nombre<br> <a href='Formulario.html'>Volver</a>";
    }else if($Email == ""){
        echo "Escriba el Email<br> <a href='Formulario.html'>Volver</a>";
    }else if($Telefono == ""){
        echo "Escriba el Teléfono<br> <a href='Formulario.html'>Volver</a>";
    }else{         
        if ($bug){
            echo "<h1>Guardado =D</h1> <br> <a href='Formulario.html'>Volver</a>";
            mysqli_query($link,"INSERT INTO formulario(nombre,email,telefono,Grado_medio,Grado_superior,privacidad) VALUES ('$Nombre','$Email','$Telefono','$Grado_Medio','$Grado_superior','$privacidad')");
        }else{
            echo 'F';
        }
    }
}else{
    echo "<h1>Obligatorio macar la politica de privacidad</h1> <br> <a href='Formulario.html'>Volver</a>";

}
?>
</html>