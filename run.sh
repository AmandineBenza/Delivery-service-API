echo 'Jean want to eat some food'
serverIp=localhost:8080
curl -X POST "http://$serverIp/DSA/UPDATE"
jsonFoodResponse= curl "http://$serverIp/DSA/CATALOG"

joesResponse='{"foods" : [{"id" : 1,"restaurantId" : 0,"price" : 13.9,"name" : "Hamburger by Sun City Cafe","description" : "Compose your own burger"}],
"menus" : []
}' 

choice='Hamburger%20by%20Sun%20City%20Cafe'
echo 'Jean chose some food in the first restaurant'
echo 'He check for the delivery time givin his address in the requests'
curl -X GET "http://$serverIp/DSA/FOOD/Dessert%20by%20Pizza%20FAFA?address=11avtruc"

echo 'He is now ordering his food'
curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d "$joesResponse" "$serverIp/DSA/FOOD/Jean%20Humbert?address=x"

echo 'The restaurant consult his order'
curl "$serverIp/DSA/RESTAURANT/Sun%20City%20Cafe/ORDERS"
echo 'Order is now ready to be sent '
curl -X POST "$serverIp/DSA/RESTAURANT/ORDERS/9"
curl POST -X "$serverIp/DSA/COURSIER/ORDERS/Pierre%20Rousseau/10"