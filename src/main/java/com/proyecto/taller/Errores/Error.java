package com.proyecto.taller.Errores;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

//La notacion data es ceunta d eos getter y setter
	@Data
	//para crear constructor vacio
	@NoArgsConstructor

	//ES UN DTO EN SI POR QUE DEVUELVE ALGO
	public class Error {
		private String message;
		private String error;
		private int status;
		private Date date;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getError() {
			return error;
		}
		public void setError(String error) {
			this.error = error;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}


	}


