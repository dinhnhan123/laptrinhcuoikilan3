package Dao;

import DBConnect.DBcontext;
import model.Account;
import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Dao {
        Connection conn = null; // kết nối đến mysql
        PreparedStatement ps = null ; // ném câu lệnh query sang mysql
        ResultSet rs = null ;// trả về kết quả

        public List<Product> getAllProduct(){
            List<Product> list = new ArrayList<>();
            String query = "SELECT * FROM `products`";
           try{
               conn = new DBcontext().getConnection();// mở kết nối với mysql
               ps = conn.prepareStatement(query); // ném câu lệnh query sang mysq
               // cuối cùng mình sẽ câu lệnh query này và sẽ trả về kết quả đó la rs
               rs = ps.executeQuery(); // như vậy nó sẽ chạy câu lệnh query này
                    while (rs.next()){
                        list.add(new Product(rs.getInt(1),
                                            rs.getString(2),
                                            rs.getString(3),
                                            rs.getInt(4),
                                            rs.getString(5),
                                            rs.getString(6)));
                    }

           }catch (Exception e){

           }
            return list;
        }

    public List<Category> getAllCategory(){
        List<Category> list = new ArrayList<>();
        String query = "SELECT*FROM category";
        try{
            conn = new DBcontext().getConnection();// mở kết nối với mysql
            ps = conn.prepareStatement(query); // ném câu lệnh query sang mysq
            // cuối cùng mình sẽ câu lệnh query này và sẽ trả về kết quả đó la rs
            rs = ps.executeQuery(); // như vậy nó sẽ chạy câu lệnh query này
            while (rs.next()){
                list.add(new Category(rs.getInt(1),
                                    rs.getString(2)));

            }

        }catch (Exception e){

        }
        return list;
    }

    public Product getLast(){ // lấy ra sản phẩm mới nhất mà sp new = id lớn nhất ,mà id lớn nhất lại nằm cuối
            String query = "SELECT * FROM `products`\n" +
                    "ORDER BY id DESC\n" +
                    "LIMIT 1";
        try{
            conn = new DBcontext().getConnection();// mở kết nối với mysql
            ps = conn.prepareStatement(query); // ném câu lệnh query sang mysq
            // cuối cùng mình sẽ câu lệnh query này và sẽ trả về kết quả đó la rs
            rs = ps.executeQuery(); // như vậy nó sẽ chạy câu lệnh query này
            while (rs.next()){
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6));

            }

        }catch (Exception e){

        }
            return null;
    }
    public List<Product> getProductByCID(String cid){ // về bản chất là kiểu  int nhưng vì kiểu int hay string thì
            // sql vẫn hiểu nên mình dùng kiểu string để tí nữa đỡ ép kiểu
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM `products`\n" +
                "WHERE cateID = ?";
        try{
            conn = new DBcontext().getConnection();// mở kết nối với mysql
            ps = conn.prepareStatement(query); // ném câu lệnh query sang mysq
            // cuối cùng mình sẽ chạy câu lệnh query này và sẽ trả về kết quả đó la rs
            ps.setString(1,cid); // truyền cid vào dấu chấm hỏi
            rs = ps.executeQuery(); // như vậy nó sẽ chạy câu lệnh query này
            while (rs.next()){
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6)));
            }

        }catch (Exception e){

        }
        return list;
    }

    public Product getProductByID(String id){ // về bản chất là kiểu  int nhưng vì kiểu int hay string thì
        // sql vẫn hiểu nên mình dùng kiểu string để tí nữa đỡ ép kiểu
        String query = "SELECT * FROM `products`\n" +
                "WHERE id = ?";
        try{
            conn = new DBcontext().getConnection();// mở kết nối với mysql
            ps = conn.prepareStatement(query); // ném câu lệnh query sang mysq
            // cuối cùng mình sẽ chạy câu lệnh query này và sẽ trả về kết quả đó la rs
            ps.setString(1,id); // truyền id vào dấu chấm hỏi thứ nhất
            rs = ps.executeQuery(); // như vậy nó sẽ chạy câu lệnh query này
            while (rs.next()){
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6));
            }

        }catch (Exception e){

        }
        return null;
    }
    public List<Product> SearchByName(String txtSearch){ // về bản chất là kiểu  int nhưng vì kiểu int hay string thì
        // sql vẫn hiểu nên mình dùng kiểu string để tí nữa đỡ ép kiểu
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM  products\n" +
                "WHERE name LIKE ?";
        try{
            conn = new DBcontext().getConnection();// mở kết nối với mysql
            ps = conn.prepareStatement(query); // ném câu lệnh query sang mysq
            // cuối cùng mình sẽ chạy câu lệnh query này và sẽ trả về kết quả đó la rs
            ps.setString(1,"%"+txtSearch+"%"); // truyền cid vào dấu chấm hỏi
            rs = ps.executeQuery(); // như vậy nó sẽ chạy câu lệnh query này
            while (rs.next()){
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6)));
            }

        }catch (Exception e){

        }
        return list;
    }
    public Account  login(String user, String pass){
            String query = "SELECT * FROM account \n" +
                    "WHERE user = ?\n" +
                    "AND pass = ?";
        try{
            conn = new DBcontext().getConnection();// mở kết nối với mysql
            ps = conn.prepareStatement(query); // ném câu lệnh query sang mysq
            // cuối cùng mình sẽ chạy câu lệnh query này và sẽ trả về kết quả đó la rs
            ps.setString(1,user); // truyền user vào dấu chấm hỏi thứ nhất
            ps.setString(2,pass); // truyền pass vào dấu chấm hỏi thứ hai
            rs = ps.executeQuery(); // như vậy nó sẽ chạy câu lệnh query này
                while (rs.next()){
                    return  new Account(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getInt(5));


                }

        }catch (Exception e){

        }
        return null;
    }
    public Account  CheckUserExist(String user){
        String query = "SELECT * FROM `account`\n" +
                "WHERE user = ?";
        try{
            conn = new DBcontext().getConnection();// mở kết nối với mysql
            ps = conn.prepareStatement(query); // ném câu lệnh query sang mysq
            // cuối cùng mình sẽ chạy câu lệnh query này và sẽ trả về kết quả đó la rs
            ps.setString(1,user); // truyền user vào dấu chấm hỏi thứ nhất
            rs = ps.executeQuery(); // như vậy nó sẽ chạy câu lệnh query này
            while (rs.next()){
                return  new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));


            }

        }catch (Exception e){

        }
        return null;
    }
    public void signUp(String user,String pass){
            String query = "INSERT INTO account(`user`,pass,isSell,isAdmin)\n" +
                    "VALUES(?,?,0,0);";
        try{
          conn = new DBcontext().getConnection();// mở kết nối với mysql
          ps = conn.prepareStatement(query); // ném câu lệnh query sang mysq
          // cuối cùng mình sẽ chạy câu lệnh query này và sẽ trả về kết quả đó la rs
          ps.setString(1,user);
          ps.setString(2,pass);
          // truyền user vào dấu chấm hỏi thứ nhất
          ps.executeUpdate();
      }catch (Exception e){

      }

    }
    public List<Product> getProductBySellID(int id){ // về bản chất là kiểu  int nhưng vì kiểu int hay string thì
        // sql vẫn hiểu nên mình dùng kiểu string để tí nữa đỡ ép kiểu
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM `products`\n" +
                "WHERE sell_ID = ?";
        try{
            conn = new DBcontext().getConnection();// mở kết nối với mysql
            ps = conn.prepareStatement(query); // ném câu lệnh query sang mysq
            // cuối cùng mình sẽ chạy câu lệnh query này và sẽ trả về kết quả đó la rs
            ps.setInt(1,id); // truyền id vào dấu chấm hỏi
            rs = ps.executeQuery(); // như vậy nó sẽ chạy câu lệnh query này
            while (rs.next()){
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6)));
            }

        }catch (Exception e){

        }
        return list;
    }
    public void delete(String id ){
            String query = "DELETE  FROM `products`\n" +
                    "WHERE id = ?";
        try {
            conn = new DBcontext().getConnection();// mở kết nối với mysql
            ps = conn.prepareStatement(query); // ném câu lệnh query sang mysq
            // cuối cùng mình sẽ chạy câu lệnh query này và sẽ trả về kết quả đó la rs
            ps.setString(1, id); // truyền id vào dấu chấm hỏi
            ps.executeUpdate(); // như vậy nó sẽ chạy câu lệnh query này
        }catch (Exception e){

        }

    }
    public void insertProduct(String name, String image, int price , String title, String description,
                              int category, int sellid){
            String query = "INSERT INTO products\n" +
                    "(name,image,price,title,description,cateID,sell_ID)\n" +
                    "VALUES(?,?,?,?,?,?,?)";
        try {
            conn = new DBcontext().getConnection();// mở kết nối với mysql
            ps = conn.prepareStatement(query); // ném câu lệnh query sang mysq
            // cuối cùng mình sẽ chạy câu lệnh query này và sẽ trả về kết quả đó la rs
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setInt(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setInt(6, category);
            ps.setInt(7, sellid);
            ps.executeUpdate(); // như vậy nó sẽ chạy câu lệnh query này
        }catch (Exception e){

        }

    }
    public void editProduct(String name, String image, int price , String title, String description,
                              int category, int pid){
        String query = "UPDATE  products\n" +
                "SET name =?,\n" +
                "image = ?,\n" +
                "price = ?,\n" +
                "title = ?,\n" +
                "description = ?,\n" +
                "cateID = ?\n" +
                "WHERE id = ?";
        try {
            conn = new DBcontext().getConnection();// mở kết nối với mysql
            ps = conn.prepareStatement(query); // ném câu lệnh query sang mysq
            // cuối cùng mình sẽ chạy câu lệnh query này và sẽ trả về kết quả đó la rs
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setInt(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setInt(6, category);
            ps.setInt(7, pid);
            ps.executeUpdate(); // như vậy nó sẽ chạy câu lệnh query này
        }catch (Exception e){

        }

    }
    public static void main(String[] args) {
        Dao dao = new Dao();
//        List<Product> list = dao.getAllProduct();
//        for (Product o:list){
//            System.out.println(o);
//        }
//        List<Category> listC = dao.getAllCategory();
//        for (Category o:listC){
//            System.out.println(o);
//        }
//        Product p = dao.getLast();
//        System.out.println(p);


//        List<Product> list = dao.getProductByCID("1");
//        for (Product o:list){
//            System.out.println(o);
//        }
//        Product pro = dao.getProductByID("1");
//        System.out.println(pro);
//        List<Product> list = dao.SearchByName("adidas");
//        for (Product o:list){
//            System.out.println(o);
//        }
//        Account account = dao.login("dinhnhan","654321");
//        System.out.println(account);
                List<Product> list = dao.getProductBySellID(1);
//        for (Product o:list){
//            System.out.println(o);
//        }
       dao.delete("19");
    }
}
