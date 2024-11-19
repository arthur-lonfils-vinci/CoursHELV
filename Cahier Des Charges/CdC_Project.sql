CREATE TABLE "users" (
  "user_id" SERIAL PRIMARY KEY,
  "firstname" TEXT,
  "lastname" TEXT,
  "email" TEXT UNIQUE,
  "password" TEXT,
  "image_path" TEXT,
  "status" TEXT,
  "is_active" BOOLEAN
);

CREATE TABLE "fees" (
  "fee_id" SERIAL PRIMARY KEY,
  "trip_id" INTEGER,
  "paid_by" INTEGER,
  "type" TEXT,
  "total_paid" DOUBLE PRECISION,
  "is_confirmed" BOOL
);

CREATE TABLE "lignes_fee" (
  "ligne_fee_id" SERIAL PRIMARY KEY,
  "fee_id" INTEGER,
  "paid_for" INTEGER,
  "montant" DOUBLE PRECISION
);

CREATE TABLE "details_fees" (
  "details_fee_id" SERIAL PRIMARY KEY,
  "trip_id" INTEGER,
  "user" INTEGER,
  "file" PATH
);

CREATE TABLE "participants" (
  "user_id" INTEGER,
  "trip_id" INTEGER,
  PRIMARY KEY ("user_id", "trip_id")
);

CREATE TABLE "trips" (
  "trip_id" SERIAL PRIMARY KEY,
  "destination" TEXT,
  "start_date" DATE,
  "end_date" DATE,
  "budget_max" DOUBLE PRECISION,
  "is_confirmed" BOOLEAN,
  "is_refunded" BOOLEAN
);

ALTER TABLE "fees" ADD FOREIGN KEY ("trip_id") REFERENCES "trips" ("trip_id");

ALTER TABLE "fees" ADD FOREIGN KEY ("paid_by") REFERENCES "users" ("user_id");

ALTER TABLE "lignes_fee" ADD FOREIGN KEY ("fee_id") REFERENCES "fees" ("fee_id");

ALTER TABLE "lignes_fee" ADD FOREIGN KEY ("paid_for") REFERENCES "users" ("user_id");

ALTER TABLE "details_fees" ADD FOREIGN KEY ("trip_id") REFERENCES "trips" ("trip_id");

ALTER TABLE "details_fees" ADD FOREIGN KEY ("user") REFERENCES "users" ("user_id");

ALTER TABLE "participants" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("user_id");

ALTER TABLE "participants" ADD FOREIGN KEY ("trip_id") REFERENCES "trips" ("trip_id");
