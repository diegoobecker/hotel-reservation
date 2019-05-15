CREATE SEQUENCE hotel_id_seq AS BIGINT START WITH 1 INCREMENT BY 1;

CREATE TABLE hotel (
  code BIGINT GENERATED BY DEFAULT AS SEQUENCE hotel_id_seq PRIMARY KEY,
  title VARCHAR(255),
  address VARCHAR(255),
  cnpj VARCHAR(255),
  description VARCHAR(255),
  priceWeekdaysRegular FLOAT,
  priceWeekdaysRewards FLOAT,
  priceWeekendRegular FLOAT,
  priceWeekendRewards FLOAT,
  rating INT,
  blackoutI DATE,
  blackoutE DATE
);

