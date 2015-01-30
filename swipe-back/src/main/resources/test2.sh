#Tests sur la partie 2 : Récupérer et créer des valeurs pour un probleme.

#Récupérer l'id d'un probleme
PROBLEM_ID=`curl -G http://178.62.199.79:8080/swipe-back/problems| jq '.[0] | .id'`

#Créer des valeurs pour le problème
curl http://178.62.199.79:8080/swipe-back/problems/$PROBLEM_ID/values --data-urlencode name="Funkyness" --data-urlencode description="Ne jamais négliger la funkytude :o" &&

curl http://178.62.199.79:8080/swipe-back/problems/$PROBLEM_ID/values --data-urlencode name="L'écologie" --data-urlencode description="Protéger la nature en mangeant des oeufs à cla coque ?" &&

curl http://178.62.199.79:8080/swipe-back/problems/$PROBLEM_ID/values --data-urlencode name="Economique" --data-urlencode description="Ca coute pas cher !" &&

echo ""
echo "********************************"
echo "VALUES : "
echo ""
curl -G http://178.62.199.79:8080/swipe-back/problems/$PROBLEM_ID/values &&



#Tests sur la partie 3 : Créer des solutions pour un probleme, récupérer ces solutions, récupérer versus et solution score associés

#Créer des solutions
curl http://178.62.199.79:8080/swipe-back/problems/$PROBLEM_ID/solutions --data-urlencode name="En manger de temps en temps" --data-urlencode description="Manger un oeuf à la coque de temps en temps permet d'avoir une alimentation variée et ne nuit pas tant que ça à l'environnement" &&

curl http://178.62.199.79:8080/swipe-back/problems/$PROBLEM_ID/solutions --data-urlencode name="Ne pas en manger du tout" --data-urlencode description="Les oeufs à la coque c'est le mal, ça nourrit les grosses corporations et donne du cholesterol" &&

curl http://178.62.199.79:8080/swipe-back/problems/$PROBLEM_ID/solutions --data-urlencode name="Manger des oeufs bio" --data-urlencode description="Les oeufs biologiques sont meilleurs pour la anté et soutiennent les petits paysans" &&

echo ""
echo "*********************************"
echo "SOLUTIONS :"
echo ""
curl -G http://178.62.199.79:8080/swipe-back/problems/$PROBLEM_ID/solutions &&
echo ""

