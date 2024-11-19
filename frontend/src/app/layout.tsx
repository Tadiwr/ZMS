import type { Metadata } from "next";
import localFont from "next/font/local";
import "./globals.css";
import TopNavBar from "@/components/navigation/TopNavBar";
import SideBar from "@/components/navigation/SideBar";

const geistSans = localFont({
  src: "./fonts/GeistVF.woff",
  variable: "--font-geist-sans",
  weight: "100 900",
});
const geistMono = localFont({
  src: "./fonts/GeistMonoVF.woff",
  variable: "--font-geist-mono",
  weight: "100 900",
});

export const metadata: Metadata = {
  title: "Party App",
  description: "Easily Managed your own party",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={`${geistSans.variable} ${geistMono.variable} antialiased`}
      >
        <div>
          <div className="h-16 w-full border-b fixed top-0 left-0" >
            <TopNavBar />
          </div>

          <div className="mt-16 flex flex-row" >
            <div className="w-64 fixed h-screen border-r" >
              <SideBar />
            </div>
            <div className="ml-64" >
              {children}
            </div>
          </div>
        </div>
      </body>
    </html>
  );
}
