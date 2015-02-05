#Tests pour la partie 1 : créer des utilisateurs, créer et consulter des problemes

#Créer quelques utilisateurs, récupérer les ids créés dans des variables
ID1=`curl http://178.62.199.79:8080/swipe-back/user --data-urlencode username=pierre_henry --data-urlencode mail=pierre_henri@testmail.com | jq .id`
ID2=`curl http://178.62.199.79:8080/swipe-back/user --data-urlencode username=miguelo --data-urlencode mail=miguelo@testmail.com | jq .id`
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

#Tests sur la partie 2 : Récupérer et créer des valeurs pour un probleme.

#Récupérer l'id d'un probleme
PROBLEM_ID=`curl -G http://178.62.199.79:8080/swipe-back/problems| jq '.[0] | .id'`

#Créer des valeurs pour le problème
VALUE_1_ID=`curl http://178.62.199.79:8080/swipe-back/problems/$PROBLEM_ID/values --data-urlencode name="Funkyness" --data-urlencode description="Ne jamais négliger la funkytude :o" | jq .id` &&

VALUE_2_ID=`curl http://178.62.199.79:8080/swipe-back/problems/$PROBLEM_ID/values --data-urlencode name="L'écologie" --data-urlencode description="Protéger la nature en mangeant des oeufs à cla coque ?" | jq .id` &&

VALUE_3_ID=`curl http://178.62.199.79:8080/swipe-back/problems/$PROBLEM_ID/values --data-urlencode name="Economique" --data-urlencode description="Ca coute pas cher !" | jq .id` &&

echo "value 1 id : "$VALUE_1_ID
echo "value 2 id : "$VALUE_2_ID
echo "value 3 id : "$VALUE_3_ID
curl http://178.62.199.79:8080/swipe-back/values/$VALUE_1_ID/valuescores --data-urlencode score=5 --data-urlencode userId=$ID1
curl http://178.62.199.79:8080/swipe-back/values/$VALUE_2_ID/valuescores --data-urlencode score=9 --data-urlencode userId=$ID1
curl http://178.62.199.79:8080/swipe-back/values/$VALUE_3_ID/valuescores --data-urlencode score=2 --data-urlencode userId=$ID1

echo ""
echo "********************************"
echo "VALUES : "
echo ""
curl -G http://178.62.199.79:8080/swipe-back/problems/$PROBLEM_ID/values &&



#Tests sur la partie 3 : Créer des solutions pour un probleme, récupérer ces solutions, récupérer versus et solution score associés

#Créer des solutions
SOLUTION_ID_1=`curl http://178.62.199.79:8080/swipe-back/problems/$PROBLEM_ID/solutions --data-urlencode name="En manger de temps en temps" --data-urlencode description="Manger un oeuf à la coque de temps en temps permet d'avoir une alimentation variée et ne nuit pas tant que ça à l'environnement" | jq .id` &&

SOLUTION_ID_2=`curl http://178.62.199.79:8080/swipe-back/problems/$PROBLEM_ID/solutions --data-urlencode name="Ne pas en manger du tout" --data-urlencode description="Les oeufs à la coque c'est le mal, ça nourrit les grosses corporations et donne du cholesterol" |jq .id` &&

SOLUTION_ID_3=`curl http://178.62.199.79:8080/swipe-back/problems/$PROBLEM_ID/solutions --data-urlencode name="Manger des oeufs bio" --data-urlencode description="Les oeufs biologiques sont meilleurs pour la anté et soutiennent les petits paysans" | jq .id` &&

echo ""
echo "*********************************"
echo "SOLUTIONS :"
echo ""
#curl -G http://178.62.199.79:8080/swipe-back/problems/$PROBLEM_ID/solutions &&
echo "" &&
REQ_PARAM=$SOLUTION_ID_1'&solutionsId='$SOLUTION_ID_2'&solutionsId='$SOLUTION_ID_3
echo "req param -> "$REQ_PARAM
echo ""
echo "user id : "$ID1
echo "problem id : "$PROBLEM_ID
curl http://178.62.199.79:8080/swipe-back/problems/$PROBLEM_ID/solutions/select --data-urlencode userId=$ID1 --data 'solutionsId='$REQ_PARAM &&
VERSUS_1_ID=`curl -G http://178.62.199.79:8080/swipe-back/problems/$PROBLEM_ID/versus/next --data-urlencode userId=$ID1 | jq .id` &&
echo 'versus 1 id : '$VERSUS_1_ID &&
curl http://178.62.199.79:8080/swipe-back/versus/$VERSUS_1_ID/versusresponses --data-urlencode userId=$ID1 --data-urlencode response=5 &&
VERSUS_2_ID=`curl -G http://178.62.199.79:8080/swipe-back/problems/$PROBLEM_ID/versus/next --data-urlencode userId=$ID1 | jq .id` &&
echo 'versus 2 id : '$VERSUS_2_ID &&
curl http://178.62.199.79:8080/swipe-back/versus/$VERSUS_2_ID/versusresponses --data-urlencode userId=$ID1 --data-urlencode response=-2 &&
VERSUS_3_ID=`curl -G http://178.62.199.79:8080/swipe-back/problems/$PROBLEM_ID/versus/next --data-urlencode userId=$ID1 | jq .id` &&
echo 'versus 3 id : '$VERSUS_3_ID &&
curl http://178.62.199.79:8080/swipe-back/versus/$VERSUS_3_ID/versusresponses --data-urlencode userId=$ID1 --data-urlencode response=1 &&
curl http://178.62.199.79:8080/swipe-back/versus/$VERSUS_3_ID/comments --data-urlencode userId=$ID1 --data-urlencode name='Bonne idée !' --data-urlencode content="Je pense que c'est intressant de voir ce probleme comme la confrontation de ces 2 idées. Aussi first !"

echo "fin tests"




