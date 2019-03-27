package purchase;

import java.util.concurrent.atomic.AtomicLong;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class PurchaseController {

	@Autowired
	PurchaseRepository purchaseRepository;

 	@RequestMapping("/checkversion")
    public String forTesting() {
        return "v1";
    }

    @CrossOrigin()
    @RequestMapping("/purchasesByNameSpace")
    public List<Purchase> findByNameSpace(@RequestParam(required=false, defaultValue="") String nameSpace) {
		List<Purchase> purchases = purchaseRepository.findByNameSpace(nameSpace);
		System.out.println("In findByNameSpace with "+ nameSpace);
        return purchases;
    }

}
