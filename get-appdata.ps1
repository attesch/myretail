
$productIDs = (13860428,52949796,52946420,16696652,53211599,53334446)

foreach ($productID in $productIDs) {
    #Write-Host "Looking up Product $productID"
    $redskyURI = 'http://redsky.target.com/v2/pdp/tcin/'+$productID+'?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics'
    
    $itemInfo = new-object -TypeName psobject -Property @{
        "id"="";
        "name"="";
        "price"=0;
    }

    try {
        $productInfo = (Invoke-RestMethod -Uri $redskyURI)
        $itemInfo.id = $productInfo.product.item.tcin
        $itemInfo.name = $productInfo.product.item.product_description.title
        $iteminfo.price = $productInfo.product.price.listPrice.price
        $itemInfo
        #$productInfo.product.price.listPrice.price
    }
    catch {

    }
}