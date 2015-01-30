#Tests pour la partie 1 : créer des utilisateurs, créer et consulter des problemes

#Créer quelques utilisateurs, récupérer les ids créés dans des variables
ID1=`curl http://178.62.199.79:8080/swipe-back/user --data-urlencode username=jean --data-urlencode mail=jean@testmail.com | jq .id`
ID2=`curl http://178.62.199.79:8080/swipe-back/user --data-urlencode username=michel --data-urlencode mail=michel@testmail.com | jq .id`
ID3=`curl http://178.62.199.79:8080/swipe-back/user --data-urlencode username=paul -d mail=paul@testmail.com | jq .id`
ID4=`curl http://178.62.199.79:8080/swipe-back/user --data-urlencode username=jacky -d mail=jacky@testmail.com | jq .id`

#curl -G http://178.62.199.79:8080/swipe-back/user -d id=$ID1
#curl -G http://178.62.199.79:8080/swipe-back/user -d id=$ID2
#curl -G http://178.62.199.79:8080/swipe-back/user -d id=$ID3
#curl -G http://178.62.199.79:8080/swipe-back/user -d id=$ID4


#Créer des problemes
curl http://178.62.199.79:8080/swipe-back/problems --data-urlencode name="Faut-il manger des oeufs à la coque ??" --data-urlencode description="D'un coté cest bon, mais en même temps cest galere à préparer et tout."
curl http://178.62.199.79:8080/swipe-back/problems --data-urlencode name="Faut-il laisser couler l'eau sous la douche ?" --data-urlencode description="Apres j'ai froid mais je veux quand meme pas gaspiller ..."
curl http://178.62.199.79:8080/swipe-back/problems --data-urlencode name="Faire un compte sur facebook ??" --data-urlencode description="J'ai envie de pécho des meufs mais je veux pas que la cia m'espionne, que faire ?"

echo ""



