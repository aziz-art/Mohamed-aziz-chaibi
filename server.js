import express from "express";
import recipeRouter from "./Routers/recipiRoutes.js";
import categoryRouter from "./Routers/categoryRouter.js";
import mongoose from "mongoose";
import forgotRouter from './Routers/forgotRouter.js';
import router from "./Routers/routers.js";
const app = express();
app.use(express.json());


app.use("/user", router);
app.use("/recipi", recipeRouter);
app.use("/Category", categoryRouter);
app.use('/api/auth', forgotRouter);

mongoose
  .connect("mongodb://127.0.0.1:27017/dbrecipi")
  .then(() => {
    console.log("db connection well");
  })
  .catch((err) => {
    console.log(err);
  });

app.listen(3000, () => {
    console.log('Server running at 3000 ...');
});
