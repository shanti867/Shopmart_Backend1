package com.example.Shopmart_Backend1.Service;

import com.example.Shopmart_Backend1.Dto.CheckoutDTO;
import com.example.Shopmart_Backend1.Entity.Cart;
import com.example.Shopmart_Backend1.Entity.Checkout;
import com.example.Shopmart_Backend1.Entity.Product;
import com.example.Shopmart_Backend1.Entity.User;
import com.example.Shopmart_Backend1.Repository.CartRepository;
import com.example.Shopmart_Backend1.Repository.CheckoutRepository;
import com.example.Shopmart_Backend1.Repository.ProductRepository;
import com.example.Shopmart_Backend1.Repository.UserRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckoutService {
    @Autowired
    private CheckoutRepository checkoutRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public Checkout createCheckout(CheckoutDTO data, String username){
        try{

            User user = userRepository.findByUsernameIgnoreCase(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            List<Cart> cartList = cartRepository.findByUser(user);

            if(cartList == null || cartList.isEmpty()){
                throw new RuntimeException("Cart is empty");
            }
            for(Cart cart: cartList){
                Optional<Product> optionalProduct = productRepository.findById(cart.getProduct().getId());

                if(optionalProduct.isEmpty()){
                    throw new RuntimeException("Product not found:"+ cart.getProduct());
                }
                Product product = optionalProduct.get();
                if(product.getStockQuantity() < cart.getQuantity()){
                    throw new RuntimeException("Not enough stock for product:" + product.getName());
                }
                int newStock = product.getStockQuantity() - cart.getQuantity();
                product.setStockQuantity(newStock);

                if(newStock == 0){
                    product.setStock(false);
                }
                else{
                    product.setStock(true);
                }
                productRepository.save(product);
            }
            Checkout checkout = new Checkout();
            checkout.setUser(username);

            checkout.setDeliveryAddress(objectMapper.writeValueAsString(data.getDeliveryAddress()));
            checkout.setOrderStatus(data.getOrderStatus());
            checkout.setPaymentMode(data.getPaymentMode());
            checkout.setPaymentStatus(data.getPaymentStatus());
            checkout.setSubtotal(data.getSubtotal());
            checkout.setShipping(data.getShipping());
            checkout.setTotal(data.getTotal());
            checkout.setDate(data.getDate());
            checkout.setProducts(objectMapper.writeValueAsString(data.getProducts()));
            Checkout savedCheckout = checkoutRepository.save(checkout);
            savedCheckout.setCheckoutId("CHK"+ String.format("%03d",savedCheckout.getId()));
            checkoutRepository.save(savedCheckout);

            String customerEmail = null;
            JsonNode addressNode = objectMapper.valueToTree(data.getDeliveryAddress());
            if(addressNode.has("email")){
                customerEmail = addressNode.get("email").asText();
            }
            if(customerEmail != null && !customerEmail.isBlank()){
                try{
                    emailService.sendOrderConfirmationEmail(customerEmail,savedCheckout.getId(),savedCheckout.getTotal());
                }
                catch(Exception e){
                    System.out.println("Order saved but  email could not be sent:"+e.getMessage());
                }
            }
            for(Cart cart: cartList){
                cartRepository.deleteById(cart.getId());
            }
            return savedCheckout;
        }
        catch(Exception e){
            throw new RuntimeException("unable to place order:"+e.getMessage());
        }
    }
    public List<Checkout> getAllCheckout(){
        return checkoutRepository.findAll();
    }
    public List<Checkout> getUserCheckout(String user){
        return checkoutRepository.findByUser(user);
    }
    public Optional<Checkout> getCheckout(Long id){
        return checkoutRepository.findById(id);
    }
    public void deleteCheckout(Long id){
        checkoutRepository.deleteById(id);
    }
}
