# Login App (MVVM + Room + Jetpack Compose)

Aplikasi login sederhana: input username & password, tombol login, validasi,
pesan sukses/gagal, dan data user disimpan di Room Database (`login_db`, tabel `users`).

## Cara menjalankan
1. Ekstrak zip, lalu di Android Studio pilih **File > Open** dan pilih folder `LoginApp`.
2. Tunggu Gradle Sync selesai (butuh internet untuk unduh dependency).
3. Jalankan di emulator / HP (minSdk 24).

## Akun dummy
| Username | Password |
|----------|----------|
| admin    | 12345    |

Disisipkan otomatis saat aplikasi pertama kali dibuka (jika tabel masih kosong).

## Skenario uji
| Input                | Hasil                                   |
|----------------------|-----------------------------------------|
| admin / 12345        | Login Berhasil – Selamat datang, admin! |
| admin / salah        | Username atau Password Salah            |
| userxxx / 12345      | Username atau Password Salah            |
| (kosong)             | Username dan password wajib diisi       |

## Struktur folder
```
com.example.loginapp
├── data
│   ├── local
│   │   ├── dao/UserDao.kt
│   │   ├── database/AppDatabase.kt
│   │   └── entity/User.kt
│   └── repository/UserRepository.kt
├── ui/screen/LoginScreen.kt
├── viewmodel/LoginViewModel.kt
└── MainActivity.kt
```

## Alur login
LoginScreen (Compose) → LoginViewModel.login() → UserRepository.login()
→ UserDao.login() → Room Database → SQLite

Cek isi database: **View > Tool Windows > App Inspection > Database Inspector**.

## Preview
<img width="318" height="640" alt="WhatsApp Image 2026-11-03 at 12 18 43" src="https://github.com/user-attachments/assets/59155a25-2b83-4e3d-86d6-1a38a80d704d" />
<img width="318" height="640" alt="WhatsApp Image 2026-11-03 at 12 18 43 (1)" src="https://github.com/user-attachments/assets/0bb38882-70fc-4de6-a558-444d87ea9696" />
<img width="318" height="640" alt="WhatsApp Image 2026-11-03 at 12 18 44" src="https://github.com/user-attachments/assets/30b0a2a2-d532-4284-af3b-253e7bc1667a" />


