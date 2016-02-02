
<HTML>
<head>
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">

	<meta charset="UTF-8">
	<meta name="description" content="Free Web tutorials">
	<meta name="keywords" content="HTML,CSS,XML,JavaScript">
	<meta name="author" content="Hege Refsnes">
</head>
<body>
<div class="container row center-block onesidedropshadowHeader blackB">
		<div class =" col-md-2">
		<br>
			<img src="images/dvd.png" height="50">
			<img src="images/action.png" height="50">
			<img src="images/vlc.png" height="50">
		</div>
	<!--Fin gauche page --> 
	<!--Centre page --> 
	<div class ="col-md-8 center-block">
		<h1 class="text-center">Location de Films <small>Projet BD numéro 3</small></h1>
	</div>
	<div class="col-md-2 ">
		<br>
			<a href="/UQAC_LAB3/"><button type="submit" class="btn btn-primary btn-lg btn-block ">Accueil</button></a>
		</div>
	</div>
</div>
	<div class="container center-block">
		<div class="row">			
		
			<div class="col-md-offset-1 col-md-10 ">
			 <h1 class="text-center">Souscrire à  un forfait</h1>
			 <br>
			 <div class="col-md-4">
				<div  id ="forfait1" onMouseOver="document.getElementById('forfait1').style.color = 'blue'" onMouseOut="document.getElementById('forfait1').style.color = 'black'" onclick='window.location.href="inscriptionFormulaireClient.jsp?=forfait1"' class="img-thumbnail onesidedropshadow"> <h2 class="text-center">Débutant</h2><h3><ul><li>1 film</li><li>10$</li><li>Durée : 10 jours</li></ul></h3><img src="images/action.png" height ="160" alt="..." ></div>
			</div>
			<div class="col-md-4">
				<div id ="forfait2" onMouseOver="document.getElementById('forfait2').style.color = 'blue';" onMouseOut="document.getElementById('forfait2').style.color = 'black'"onclick='window.location.href="inscriptionFormulaireClient.jsp?=forfait2"' class="img-thumbnail onesidedropshadow"> <h2 class="text-center">Intermédiaire</h2><h3><ul><li>5 films</li><li>15$</li><li>Durée : 30 jours</li></ul></h3><img src="images/forfaitMax.jpg" height ="200" alt="..." ></div>
			</div>
			<div class=" col-md-4">
				<div id ="forfait3" onMouseOver="document.getElementById('forfait3').style.color = 'blue'" onMouseOut="document.getElementById('forfait3').style.color = 'black'"onclick='window.location.href="inscriptionFormulaireClient.jsp?=forfait3"' class="img-thumbnail onesidedropshadow"> <h2 class="text-center">Avancé</h2><h3><ul><li>10 films</li><li>20$</li><li>Durée : illimité </li></ul></h3><img src="images/forfait.jpg" height ="240"alt="..." ></div>
				</div>
			</div>
		</div>
	</div>
</body>
</HTML>