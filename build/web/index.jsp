<%@ page pageEncoding="UTF-8" %>


<!DOCTYPE html>
<HTML>
    <!-- Header --> 
    <head>
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">
        <meta charset="UTF-8">
        <meta name="keywords" content="HTML,CSS,JavaScript">
        <meta name="author" content="Alban Bertolini">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

    </head>
    <!-- Fin Header -->

    <body>
        <div class="container">

            <div class="row">
                <!--Gauche page --> 
                <div class="container row blackB onesidedropshadowHeader center-block">
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
                    <div class="col-md-2">
                        <h4>BERTOLINI Alban</h4>
                        <h4>ROSAZ Lucas</h4>
                    </div>
                </div>

                <div class=" col-sm-10">
                    </br>
                    </br>
                    ${form.erreurs['membre']}
                    <form method ="POST" action ="Connexion" class="form-horizontal"  onsubmit="return verificationFormulaire(this)">
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">Identifiant</label >
                            <div class="col-sm-10">
                                <input type="text" class="form-control"  name ="identifiant" id="id" placeholder="Courriel" onblur="verifPseudo(this)">
                                ${form.erreurs['identifiant']}
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">Mot de passe</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control"  name ="mdp" id="mdp" placeholder="Mot de Passe" onblur="verifMDP(this)"/>
                                ${form.erreurs['mdp']}
                            </div>

                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-primary btn-lg btn-block">Se connecter</button>
                            </div>
                        </div>
                    </form>

                    <!-- Fin Centre page --> 
                    <div class="text-center ">
                        <h2> Les films du moment</h2>

                        <a  href=""> <img  onclick="connecter()" class ="onesidedropshadow"src="images/pirate.jpg" height="150"></a>

                        <a  href=""> <img  onclick="connecter()" class ="onesidedropshadow " src="images/veryBadTrip.jpg" height="180"></a>

                        <a  href=""> <img  onclick="connecter()" class ="onesidedropshadow img-thumbnail" src="images/titanic.jpg"></a>

                        <a  href=""> <img  onclick="connecter()" class ="onesidedropshadow" src="images/twilight.jpg" height="180"></a>

                        <a  href=""> <img  onclick="connecter()"class ="onesidedropshadow" src="images/leon.jpg" height="150"></a>
                    </div>
                </div>
                <div class ="col-md-2 ">
                    <br>
                    <a href="inscriptionForfait.jsp "><button type="submit" class="btn btn-warning btn-lg btn-block ">S'inscrire</button></a>
                    <br>
                    <h3 class="text-center"> Forfaits </h3>

                    <a href="inscriptionForfait.jsp" class="img-thumbnail"> <div class=" container  row col-sm-offset-1 col-md-10">
                            <div> <h4 class="text-center">Débutant</h4><img  class="img-responsive" src="images/action.png"  alt="..." ></div>
                        </div>
                        <div class="container row col-sm-offset-1 col-md-10">
                            <div> <h4 class="text-center">Intermédiaire</h4><img class="img-responsive" src="images/forfaitMax.jpg"  alt="..." ></div>
                        </div>
                        <div class="container row col-sm-offset-1 col-md-10">
                            <div> <h4 class="text-center">Avancé</h4><img class="img-responsive" src="images/forfait.jpg" alt="..." ></div>
                        </div>
                    </a>
                </div>

                <!-- CAROUSSEL -->

            </div>
        </div>
    </body>

    <!--Javascript-->
    <script type="text/javascript">
        function surligne(champ, erreur)
        {
            if (erreur)
                champ.style.backgroundColor = "#fba";
            else
                champ.style.backgroundColor = "#BEF574";
        }

        function connecter() {
            alert('Vous devez vous connecter avant de pouvoir louer ou consulter un film');
        }

        function verificationFormulaire(f) {
            var pseudoOk = verifPseudo(f.pseudo);
            var mdp = verifMDP(f.mdp);

            if (pseudoOk && mdp)
                return true;
            else
            {
                alert("Veuillez remplir correctement votre Identifiant et mot de Passe");
                return false;
            }
        }

        function verifPseudo(champ)
        {
            if (champ.value.length < 5 || champ.value.length > 25)
            {
                surligne(champ, true);
                return false;
            } else
            {
                surligne(champ, false);
                return true;
            }
        }

        function verifMDP(champ)
        {
            if (champ.value.length < 5 || champ.value.length > 25)
            {
                surligne(champ, true);
                return false;
            } else
            {
                surligne(champ, false);
                return true;
            }
        }
    </script>
</HTML>