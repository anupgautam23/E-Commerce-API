package com.masai.Model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
	
	@NotNull(message = "userid is mandate")
	private Integer userId;
	
	@NotNull(message = "product id is mandatory")
	private Integer productId;
	
	@NotNull(message = "product quantity is mandatory")
	private Integer productQty;

}
