/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.shopping.Cart;
import sample.utils.Utils;

/**
 *
 * @author ASUS
 */
public class UserDAO {

    private static final String CONNECTING = "SELECT fullName, roleID, address, birthday, phone, email, status"
            + " FROM tblUsers"
            + " WHERE userID =? AND password=?";

    private static final String DUPLICATE = "SELECT fullName "
            + "FROM tblUsers "
            + "WHERE userID = ?";

    private static final String DUPLICATE_EMAIL = "SELECT fullName "
            + "FROM tblUsers "
            + "WHERE email = ?";

    private static final String CREATE = "INSERT INTO tblUsers(userID, fullName, password, roleID, address, birthday, phone, email, status) VALUES (?,?,?,?,?,?,?,?,?)";

    private static final String LIST = "SELECT productID, productName, image,price, quantity, tblCategory.categoryID, categoryName,importDate,usingDate"
            + " FROM tblCategory, tblProduct"
            + " WHERE tblCategory.categoryID = tblPRODUCT.categoryID AND productName like ?"
            + " ORDER BY usingDate ASC";

    private static final String LIST_CATEGORY = "SELECT * FROM tblCategory ";

    private static final String CREATE_PRODUCT = "INSERT INTO [dbo].[tblPRODUCT]"
            + " ([productID],[productName],[image],[price],[quantity],[categoryID],[importDate],[usingDate])"
            + " VALUES (?,?,?,?,?,?,?,?)";

    private static String DUPLICATE_PRODUCT = "SELECT productName "
            + "FROM tblProduct "
            + "WHERE productID = ?";

    private static final String DELETE_PRODUCT = "DELETE tblProduct WHERE productID = ?";

    private static final String QUANTITY_PRODUCT = "SELECT quantity FROM tblProduct WHERE productID = ?";

    private static final String UPDATE_PRODUCT = "UPDATE tblProduct SET productName=?, image=?, price=?,quantity=? WHERE productID=?";

    private static final String SEARCH_PRODUCT = "SELECT productID,productName, image,price, quantity,categoryID,importDate,usingDate FROM tblProduct "
            + "WHERE productName like ? "
            + "ORDER BY usingDate ASC "
            + "OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";

    private static final String GET_A_PRODUCT = "SELECT * FROM tblProduct WHERE productID = ?";

    public ProductDTO getProduct(String id) throws SQLException {
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();
            stm = conn.prepareStatement(GET_A_PRODUCT);
            stm.setString(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                product = new ProductDTO(id, rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getString(6), "", rs.getString(7), rs.getString(8));
            }
        } catch (Exception e) {

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return product;
    }

    public boolean checkout(Cart cart) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        PreparedStatement stm2 = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();
            int comp = 0;
            for (ProductDTO product : cart.getCart().values()) {
                stm = conn.prepareStatement(QUANTITY_PRODUCT);
                String sql = "UPDATE tblProduct SET quantity = ? WHERE productID=?";
                stm2 = conn.prepareStatement(sql);
                stm.setString(1, product.getProductID());
                rs = stm.executeQuery();
                if (rs.next()) {
                    comp = rs.getInt("quantity");
                    if (comp >= product.getQuantity() && comp > 0) {
                        stm2.setInt(1, comp - product.getQuantity());
                        stm2.setString(2, product.getProductID());
                        check = stm2.executeUpdate() > 0 ? true : false;
                    } else {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            check = false;
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
                stm2.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;
    }

    public List<ProductDTO> getListProduct(String search, int index) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(SEARCH_PRODUCT);
                stm.setString(1, "%" + search + "%");
                stm.setInt(2, (index - 1) * 12);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    String categoryID = rs.getString("categoryID");
                    String importDate = rs.getString("importDate");
                    String usingDate = rs.getString("usingDate");
                    list.add(new ProductDTO(productID, productName, image, price, quantity, categoryID, "", importDate, usingDate));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean updateProduct(ProductDTO product) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Utils.getConnection();
            stm = conn.prepareStatement(UPDATE_PRODUCT);
            stm.setString(1, product.getProductName());
            stm.setString(2, product.getImage());
            stm.setFloat(3, product.getPrice());
            stm.setInt(4, product.getQuantity());
            stm.setString(5, product.getProductID());
            check = stm.executeUpdate() > 0 ? true : false;
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkDuplicateProduct(String productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(DUPLICATE_PRODUCT);
                stm.setString(1, productID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;

    }

    public boolean deleteProduct(String productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Utils.getConnection();
            stm = conn.prepareStatement(DELETE_PRODUCT);
            stm.setString(1, productID);
            check = stm.executeUpdate() > 0 ? true : false;
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean createProduct(ProductDTO product) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Utils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CREATE_PRODUCT);
                stm.setString(1, product.getProductID());
                stm.setString(2, product.getProductName());
                stm.setString(3, product.getImage());
                stm.setFloat(4, product.getPrice());
                stm.setInt(5, product.getQuantity());
                stm.setString(6, product.getCategoryID());
                stm.setString(7, product.getImportDate());
                stm.setString(8, product.getUsingDate());
                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public List<CategoryDTO> getListCategory() throws SQLException {
        List<CategoryDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(LIST_CATEGORY);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String categoryID = rs.getString("categoryID");
                    String categoryName = rs.getString("categoryName");
                    list.add(new CategoryDTO(categoryID, categoryName));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public UserDTO getConnection(String userID, String password) {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();
            ps = conn.prepareStatement(CONNECTING);
            ps.setString(1, userID);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                String fullName = rs.getString("fullName");
                String roleID = rs.getString("roleID");
                String address = rs.getString("address");
                int birthday = rs.getInt("birthday");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String status = rs.getString("status");
                user = new UserDTO(userID, fullName, password, roleID, address, birthday, phone, email, status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return user;

    }

    public List<ProductDTO> getListProduct(String search) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(LIST);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    String categoryID = rs.getString("categoryID");
                    String categoryName = rs.getString("categoryName");
                    String importDate = rs.getString("importDate");
                    String usingDate = rs.getString("usingDate");
                    list.add(new ProductDTO(productID, productName, image, price, quantity, categoryID, categoryName, importDate, usingDate));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean checkDuplicate(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(DUPLICATE);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkDuplicateEmail(String email) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(DUPLICATE_EMAIL);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean createUser(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Utils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CREATE);
                stm.setString(1, user.getUserID());
                stm.setString(2, user.getFullName());
                stm.setString(3, user.getPassword());
                stm.setString(4, user.getRoleID());
                stm.setString(5, user.getAddress());
                stm.setInt(6, user.getBirthday());
                stm.setString(7, user.getPhone());
                stm.setString(8, user.getEmail());
                stm.setString(9, user.getStatus());
                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
