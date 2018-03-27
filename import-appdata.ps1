
$uri = "http://localhost:8090/products"

$products = gc ./import.json | ConvertFrom-Json

foreach ($product in $products) {
    #Write-host "Invoke-RestMethod -Method POST -ContentType `"application/json`" -Uri $uri -Body $($product | convertto-json)"
    Invoke-RestMethod -Method POST -ContentType "application/json" -Uri $uri -Body $($product | convertto-json)
}