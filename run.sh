echo 'Joe want to eat some food'
serverIp=192.168.99.100:8080
jsonFoodResponse= curl "http://$serverIp/DSA/FOOD"

(echo $jsonFoodResponse) >> ok.txt

joesResponse='{"foods" : [{"id" : 1,"restaurantId" : 0,"price" : 13.9,"name" : "Hamburger by Sun City Cafe","description" : "Compose your own burger"}],
"menus" : []
}' 
echo $joesResponse
curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d "$joesResponse" "http://$serverIp/DSA/FOOD/CHINESE?address=10%20Avenue%20de%20la%20porte%20des%20etoiles%20Nice%2006600&clientid=10000" #TODO remove client id
