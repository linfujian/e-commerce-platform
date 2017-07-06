package git.com.postgraduate.bookstore.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import git.com.postgraduate.bookstore.dao.ProductDAO;
import git.com.postgraduate.bookstore.model.ProductInfo;

public class ProductInfoValidator implements Validator{

	@Autowired
	private ProductDAO productDAO;
	
	public boolean supports(Class<?> clazz) {
		return clazz == ProductInfo.class;
	}

	public void validate(Object targrt, Errors errors) {
		ProductInfo productInfo = (ProductInfo) targrt;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "NotEmpty.productForm.code");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.productForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.productForm.price");
		
		Long code = productInfo.getCode();
		//TODO
		
	}

}
