package com.techelevator.tenmo.model;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

    public class Transfer {

        private int id;
        private int type;
        private int status;
        private int accountFrom;
        private int accountTo;
        @Positive
        private BigDecimal amount;

        public Transfer() { }

        public Transfer(int id, int type, int status, int accountFrom, int accountTo, BigDecimal amount) {
            this.id = id;
            this.type = type;
            this.status = status;
            this.accountFrom = accountFrom;
            this.accountTo = accountTo;
            this.amount = amount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {this.status = status;}

        public int getAccountFrom() {
            return accountFrom;
        }

        public void setAccountFrom(int accountFrom) {
            this.accountFrom = accountFrom;
        }

        public int getAccountTo() {
            return accountTo;
        }

        public void setAccountTo(int accountTo) {
            this.accountTo = accountTo;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {this.amount = amount;}

        @Override
        public String toString() {
            return "Transfer{" +
                    "id=" + id +
                    ", type=" + type +
                    ", status=" + status +
                    ", accountFrom=" + accountFrom +
                    ", accountTo=" + accountTo +
                    ", amount=" + amount +
                    '}';
        }
    }

