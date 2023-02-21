<%-- 
    Document   : productLists
    Created on : 9 juil. 2021, 08:01:09
    Author     : Manda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@page import="javax.servlet.http.HttpSession" %>
<%@page import="modele.Produit" %>
<%@page import="modele.Marque" %>
<%@page import="modele.Categorie"  %>
<% Vector<Produit> listeProduits = (Vector<Produit>) (request.getAttribute("listeProduits"));%>
<% Vector<String> marqueParProduit = (Vector<String>) (request.getAttribute("marqueParProduit"));  %>
<% Vector<String> categoriesParProduits = (Vector<String>) (request.getAttribute("categorieParProduit"));  %>
<% Vector<Marque> marques = (Vector<Marque>) (request.getAttribute("marques")); %>
<% Vector<Categorie> categories = (Vector<Categorie>) (request.getAttribute("categories")); %>
<% Vector<String> images = (Vector<String>) (request.getAttribute("images")); %>
<% int nombreProduits = (int) request.getAttribute("nombreProduits"); %>


<script>
    function showSlider() {
        if (document.getElementById("filtrePrix").checked === false) {
            document.getElementById("slider-range").style.display = "none";
            document.getElementById("amount").style.display = "none";
            document.getElementById("amountLabel").style.display = "none";
            document.getElementById("prixMin").value = 0;
            document.getElementById("prixMax").value = 0;
        } else {
            document.getElementById("slider-range").style.display = "block";
            document.getElementById("amount").style.display = "block";
            document.getElementById("amountLabel").style.display = "block";
        }
    }
</script>

<script>
    $(function () {
        $("#slider-range").slider({
            range: true,
            min: 0,
            max: 500,
            values: [75, 300],
            slide: function (event, ui) {
                $("#amount").val("€" + ui.values[ 0 ] + " - €" + ui.values[ 1 ]);
                document.getElementById("prixMin").value = ui.values[0];
                document.getElementById("prixMax").value = ui.values[1];
            }
        });
        $("#amount").val("$" + $("#slider-range").slider("values", 0) +
                " - $" + $("#slider-range").slider("values", 1));
    });
</script>

<div class="container col-12">
    <div class="row">
        <div class="filtre col-3" style="margin: 2vh; background-color: whitesmoke; border-radius: 10px; height: 50vh;">
            <form action="RechercheProduit" method="GET" class="form-check">
                <input type="text" placeholder="Rechercher un produit" name="recherche">
                <label><input type="checkbox" id="filtrePrix" onchange="showSlider()"> Avec Marge prix</label>
                <p>
                    <label for="amount" id="amountLabel" style="display: none;">Prix :</label>
                    <input type="text" id="amount" readonly style="border:0; color:#f6931f; font-weight:bold;display: none;">
                </p>
                <input type="number" name="prixMin" id="prixMin" hidden>
                <input type="number" name="prixMax" id="prixMax" hidden>
                <div id="slider-range" style="display: none;"></div>
                <label for="marque"> Marque: </label>
                <select name="marque" id="marque">
                    <option value="tous">Tous</option>
                    <% for (int i = 0; i < marques.size(); i++) { %>
                    <option value="<% out.print(marques.get(i).getLibeleMarque()); %>">
                        <% out.print(marques.get(i).getLibeleMarque()); %></option>
                        <% } %>
                </select>
                <br>
                <label for="categorie">Categorie:</label>
                <select name="categorie" id="categorie">
                    <option value="tous">Tous</option>
                    <% for (int i = 0; i < categories.size(); i++) { %>
                    <option value="<% out.print(categories.get(i).getLibeleCategorie()); %>">
                        <% out.print(categories.get(i).getLibeleCategorie()); %></option>
                        <% } %>
                </select><br>
                <label for="date">Date de sortie:</label>
                <input type="date" name="dateSortie">
                <br>
                <br>
                <button type="submit" class="btn btn-primary" style="padding: 1vh 3vw;background-color: rgb(255, 127, 80);border-color: rgb(255, 127, 80); margin-left: 25%;">Rechercher</button>
            </form>
        </div>
        <div class="productList col-8">
            <% for (int i = 0; i < 5; i++) { %>
            <div class="row col-12">
                <% for (int j = 2 * i; j < 2 * i + 2 && j < listeProduits.size(); j++) {%>
                <div class="col-md-6">
                    <a href="InformationProduit?idProduit=<% out.print(listeProduits.get(j).getIdProduit()); %>">
                        <div class="card mb-4 shadow-sm">
                            <img src="<% out.print(images.get(j));%>" alt="test" height="300">
                            <div class="card-body">
                                <p class="card-text"><%=listeProduits.get(j).getNomProduit()%></p>
                                <p>Categorie: <%=categoriesParProduits.get(j)%> | Marque: <%=marqueParProduit.get(j)%></p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <% if (session.getAttribute("email") != null) { %>
                                    <div class="btn-group">
                                        <a href="AjoutPanier?idProduit=<% out.print(listeProduits.get(j).getIdProduit());  %>"><button type="button" class="btn btn-sm btn-outline-secondary">Ajouter au panier</button></a>
                                    </div>
                                    <% }%>
                                    <small class="text-muted"><%=listeProduits.get(j).getPrixUnitaire()%>.00 €</small>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <% } %>
            </div>
            <% }%>
        </div>
    </div>

    <div class="row">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <li
                    class="page-item <% if (session.getAttribute("page") != null) {
                            if ((int) session.getAttribute("page") == 1) {
                                out.println("disabled");
                            }
                        } %>">
                    <a class="page-link"
                       href="ListeProduit?page=<% out.println((int) session.getAttribute("page") - 1); %>" tabindex="-1"
                       aria-disabled="true">Previous</a>
                </li>
                <li
                    class="page-item <% if (session.getAttribute("page") != null) {
                            if ((int) session.getAttribute("page") == 1) {
                                out.println("disabled");
                            }
                        } %>">
                    <a class="page-link" href="ListeProduit?page=1">1</a>
                </li>
                <% int nombrePages = nombreProduits / 10;
                    for (int i = 2; i <= nombrePages; i++) {
                %>
                <li
                    class="page-item <% if (session.getAttribute("page") != null) {
                            if ((int) session.getAttribute("page") == i) {
                                out.println("disabled");
                            }
                        } %>">
                    <a class="page-link" href="ListeProduit?page=<% out.println(i); %>"><% out.println(i); %></a>
                </li>
                <%
                    }
                %>
                <li
                    class="page-item <% if (session.getAttribute("page") != null) {
                            if ((int) session.getAttribute("page") == nombrePages) {
                                out.println("disabled");
                            }
                        } %>">
                    <a class="page-link"
                       href="ListeProduit?page=<% out.println((int) session.getAttribute("page") + 1);%>">Next</a>
                </li>
            </ul>
        </nav>
    </div>
</div>