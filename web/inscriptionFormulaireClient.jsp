<HTML>
<script type="text/javascript"> 
	function afficherForfait(){
		var url= document.URL;
		var forfait = /\*?forfait[0-3]/.exec(url)
		if (forfait=="forfait2") 
			{
				document.write(" <br><div> <h4>Vous avez souscrit au forfait : Intermédiaire</h4><img  class='img-responsive'src ='images/forfaitMax.jpg'><br></div>");

			};
			if (forfait=="forfait1") 
			{
				document.write(" <br><div><h4> Vous avez souscrit au forfait : Débutant </h4><img  class='img-responsive'src ='images/action.png'><br></div>");

			};
			if (forfait=="forfait3") 
			{
				document.write(" <br><div><h4> Vous avez souscrit au forfait : Avancé</h4><img  class='img-responsive'src ='images/forfait.jpg'><br></div>");

			};
	}
</script> 
<!--Header-->
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
<!--Fin Header-->
<div class="container row center-block">
<div class=' col-sm-3 text-center'>
	<script type="text/javascript">	document.onload = afficherForfait() </script>
</div>
<div class=" col-sm-8 ">
	</br>

	</br>
		<form class="form-horizontal"  onsubmit="return verificationFormulaire(this)">
		  <div class="form-group">
		    <label class="col-sm-2 control-label">Nom de famille</label >
		    <div class="col-sm-10">
		      <input  type ="text" class="form-control" name ="nf" id="nf" >
		    </div>
		  </div>
		  	<div class="form-group">
		    <label class="col-sm-2 control-label">Prénom</label >
		    <div class="col-sm-10">
		      <input  type ="text" class="form-control" name ="prenom" id="prenom" >
		    </div>
		  </div>
		  	<div class="form-group">
		    <label class="col-sm-2 control-label">Télephone</label >
		    <div class="col-sm-10">
		      <input  type ="text" class="form-control" name ="tel" id="tel" >
		    </div>
		    </div>
		     <div class="form-group">
		    <label class="col-sm-2 control-label">Date de naissance</label >
		    <div class="col-sm-10">
		      <input type ="text"  class="form-control" name ="date" id="date" >
		    </div>
		    </div>
		    <div class="form-group">
		    <label class="col-sm-2 control-label">Mot de Passe</label >
		    <div class="col-sm-10">
		      <input type="password" class="form-control" name ="mdp" id="mdp" >
		    </div>
		    </div>
		    <div class="list-group-item">
		    <h4>Carte de crédit</h4>
		      <div class="form-group">
		    <label class="col-sm-2 control-label">Numéro </label >
		    <div class="col-sm-10">
		      <input  type ="text" class="form-control" name ="mdp" id="mdp" >
		    </div>
		  </div>
		   <div class="form-group">
		    <label class="col-sm-2 control-label">Date d'expiration</label >
		    <div class="col-sm-10">
		      <input  type ="text" class="form-control" name ="mdp" id="mdp" >
		    </div>
		    </div>
		    <div class="form-group">
			    <label class="col-sm-2 control-label">Type de carte</label >
			    <div class="col-sm-10">
			    <select class="form-control">
				  <option>VISA</option>
				  <option>MASTERCard</option>
				  <option>Amex</option>
				</select>
			</div>
		  </div>
		  </div>
		  </br>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		 	<button type="submit" class="btn btn-primary btn-lg btn-block">Valider</button>
		    </div>
		  </div>
		</form>
</div>
</div>
</body>

</HTML>
