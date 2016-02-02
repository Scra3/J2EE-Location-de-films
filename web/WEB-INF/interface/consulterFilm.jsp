<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<head>
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">
    <meta charset="UTF-8">
    <meta name="description" content="Page de consultation de films">
    <meta name="keywords" content="HTML,CSS,XML,JavaScript">
    <meta name="author" content="Alban Bertolini">
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

            <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
            Bonjour ${sessionScope.sessionUtilisateur.nomfamille}, Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.courriel}

        </div>
        <div class="col-md-2 ">
            <br>
            <a href="Deconnexion"><button type="submit" class="btn btn-primary btn-lg btn-block ">Deconnexion</button></a>
        </div>
    </div>
</div>
<div class="container ">
    <div clas="center-block">
        <br>
        <nav class="navbar navbar-inverse onesidedropshadowHeader">
            <form class="form-vertical"  method ="POST" action ="Consultation" >
                <div class="col-md-3">
                    <div class="form-group">
                        <div class="col-md-12">
                            <input type="text" class="form-control" name ="titreFilm" id="titreFilm" placeholder="Titre du Film" >
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <div class="col-md-12">
                            <input type="text" class="form-control" name ="intervalleAnnee" id="intervalleAnnee" placeholder="Intervalle : année1/année2">
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <div class="col-md-12">
                            <select name="production" class="form-control">
                                <option>Pays de production</option>
                                <option>France</option>
                                <option>Etats-Unis</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <div class="col-md-12">
                            <select name="langue" class="form-control">
                                <option>Langue originale du film</option>
                                <option>Francais</option>
                                <option>Anglais</option>
                            </select>
                        </div>
                    </div>
                </div>
                <br>
                <br>
                <div class="col-md-3">
                    <div class="form-group">
                        <div class="col-md-12">
                            <select name="genre" class="form-control">
                                <option>Genre</option>
                                <option>Drame</option>
                                <option>Romance</option>
                                <option>Aventure</option>
                                <option>Fantastique</option>
                                <option>Action</option>
                                <option>Comédie</option>
                                <option>Policier</option>

                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <div class="col-md-12">
                            <input type="text" class="form-control" name ="nomRealisateur" id="nomRealisateur" placeholder="Nom réalisateur">
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <div class="col-md-12">
                            <input type="text" class="form-control" name ="nomActeur" id="nomActeur" placeholder="Nom acteur">
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <div class="col-md-12">
                            <button type="submit" class=" btn-sm btn-success btn-lg btn-block ">Rechercher</button>
                        </div>
                    </div>
            </form>
        </nav>

        ${form.erreurs['titreFilm']}
        ${form.erreurs['intervalleAnnee']}
        ${form.erreurs['nomRealisateur']}
        ${form.erreurs['nomActeur']}
        ${form.erreurs['membre']}

        <div class=" col-md-12 text-center center-block">

            <!--Affichage des films -->
            <c:forEach items="${films}" var="afficher" begin="0">
                ${afficher}
            </c:forEach>
            ${affiche}
        </div>
    </div>

