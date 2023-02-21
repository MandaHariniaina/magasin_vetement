<%-- 
    Document   : contenuPanier
    Created on : 24 juil. 2021, 18:16:15
    Author     : ACER
--%>

<%@page import="modele.Produit" %>
<%@page import="modele.Panier" %>
<%@page import="java.util.Vector" %>
<% Vector<Panier> listePaniers = (Vector<Panier>) request.getAttribute("listePaniers");  %>
<% Vector<Produit> produitAssocie = (Vector<Produit>) request.getAttribute("listeProduits");  %>
<% Vector<String> images = (Vector<String>) request.getAttribute("images");  %>
<% double balance = (double) request.getAttribute("balance");  %>
<% double prixTotal = 0;%>

<script>
    function update(idPanier, prixUnitaire, email) {
        var quantite = document.getElementById(idPanier).value;
        var prixTotal = document.getElementById("prixTotal").innerHtml;

        $.ajax({
            url: 'UpdatePanier',
            data: {
                idPanier: idPanier,
                quantite: quantite,
                idUtilisateur : email
            },
            success: function (data) {
                document.getElementById(idPanier).value = quantite;
                document.getElementById("prixTotal").innerText = data.split(",")[0];
                document.getElementById("prix"+idPanier).innerText = data.split(",")[1];
            }
        });
    }
</script>

<div class="container col-7">
    <div class="row"><h3>Votre compte : <%=balance%> <h3></div>
                <%for (int i = 0; i < listePaniers.size(); i++) {%>
                <div class="card mb-3" style="max-width: 50vw;">
                    <div class="row g-0">
                        <div class="col-md-4">
                            <img src="<%=images.get(i)%>" class="img-fluid rounded-start" alt="...">
                        </div>
                        <div class="col-md-6">
                            <div class="card-body">
                                <h5 class="card-title"><%=produitAssocie.get(i).getNomProduit()%></h5>
                                <p class="card-text" style="font-size: 12px;color: #51585e"><%=produitAssocie.get(i).getDescription()%></p>
                                <p class="card-text">Quantité: <%=listePaniers.get(i).getIdPanier()%>
                                <form method="POST" action="modifierQuantitePanier?idPanier=<%=listePaniers.get(i).getIdPanier()%>">
                                    <input name="quantite" id="<%=listePaniers.get(i).getIdPanier()%>" onchange="update(<%=listePaniers.get(i).getIdPanier()%>, <%=produitAssocie.get(i).getPrixUnitaire()%>, '<%=session.getAttribute("email") %>')" type="number" min=1 value="<%=listePaniers.get(i).getQuantite()%>">
                                    <button type="submit">Valider votre modification</button>
                                </form>
                                </p>
                                <p class="card-text">Prix unitaire:<small class="text-muted"><%=produitAssocie.get(i).getPrixUnitaire()%></small></p>
                                <p class="card-text">Total: <span id="prix<%=listePaniers.get(i).getIdPanier() %>"><%=listePaniers.get(i).getQuantite() * produitAssocie.get(i).getPrixUnitaire()%></span></p>
                                <% prixTotal += listePaniers.get(i).getQuantite() * produitAssocie.get(i).getPrixUnitaire();%>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <a href="EnleverPanier?idPanier=<%=listePaniers.get(i).getIdPanier()%>">Enlever</a>
                        </div>
                    </div>
                </div>
                <% }%>
                <div class="row">
                    <p>Valeur panier : <span id="prixTotal" ><% out.print(prixTotal);%></span></p>
                    <a href="ValiderAchat" style="float: left;"><h2>Valider</h2></a>
                </div>    
                </div>
