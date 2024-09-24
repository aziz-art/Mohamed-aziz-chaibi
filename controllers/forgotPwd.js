import nodemailer from 'nodemailer';
import User from '../models/user.js';

export async function forgotPassword(req, res) {
  try {
    const { email } = req.body;
    const user = await User.findOne({ email });

    if (!user) {
      return res.status(404).json({ message: 'User not found' });
    }

    // Generate a verification code and set expiration
    const verificationCode = Math.floor(100000 + Math.random() * 900000); // Generates a 6-digit code
    user.verificationCode = verificationCode;
    user.verificationCodeExpires = Date.now() + 3600000; // 1 hour
    await user.save();

    // Send the email
    const transporter = nodemailer.createTransport({
      pool: true,
      host: "smtp.gmail.com",
      port: 587,
      secure: false,
      requireTLS: true,
      auth: {
        user: 'hammamichayma825@gmail.com', 
        pass: 'ngwxfntxvsjruhnb', 
      },
      tls: {
        rejectUnauthorized: false
      },
    });

    const mailOptions = {
      to: user.email,
      from: 'hammamichayma825@gmail.com',
      subject: 'Password Reset Verification Code',
      text: `You are receiving this because you (or someone else) have requested the reset of the password for your account.\n\n
        Your verification code is: ${verificationCode}\n\n
        Please enter this code in the password reset page to proceed.\n\n
        If you did not request this, please ignore this email and your password will remain unchanged.\n`,
    };

    await transporter.sendMail(mailOptions);
    res.status(200).json({ message: 'Verification code sent to your email' });
  } catch (err) {
    res.status(500).json({ message: 'Server error', error: err.message });
  }
}


export async function resetPassword(req, res) {
  try {
    const { email, verificationCode, newPassword } = req.body;

    if (!email || !verificationCode || !newPassword) {
      return res.status(400).json({ message: 'Email, verification code, and new password are required' });
    }

    // Find the user with the provided email and verification code, and check if the code is still valid
    const user = await User.findOne({
      email: email,
      verificationCode: verificationCode,
      verificationCodeExpires: { $gt: Date.now() },
    });

    if (!user) {
      return res.status(400).json({ message: 'Invalid or expired verification code' });
    }

    // Set the new password and clear the verification code fields
    user.password = User.hashPassword(newPassword);
    user.verificationCode = undefined;
    user.verificationCodeExpires = undefined;
    await user.save();

    res.status(200).json({ message: 'Password has been reset' });
  } catch (err) {
    console.error(err);
    res.status(500).json({ message: 'Server error', error: err.message });
  }
}


