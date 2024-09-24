import {Router} from 'express';
import { forgotPassword,resetPassword } from '../controllers/forgotPwd.js';

let forgotRouter = Router();

forgotRouter.post('/forgot-password', forgotPassword);
forgotRouter.post('/reset-password', resetPassword);

export default forgotRouter;