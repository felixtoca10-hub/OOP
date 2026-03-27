#include <iostream>
#include <string>
using namespace std;

class CuentaBancaria {
private:
    string titular;
    double saldo;

public:
    CuentaBancaria(string titular, double saldoInicial) {
        this->titular = titular;
        this->saldo = saldoInicial;
    }

    string getTitular() { return titular; }
    double getSaldo() { return saldo; }

    void depositar(double monto) {
        if (monto > 0) saldo += monto;
    }

    void retirar(double monto) {
        if (monto > 0 && monto <= saldo) saldo -= monto;
    }

    void mostrar() {
        cout << "Titular = " << titular << "\n";
        cout << "Saldo = " << saldo << "\n";
    }
};

int main() {
    CuentaBancaria c1("Maria", 100000.0);
    c1.mostrar();

    c1.depositar(25000.0);
    c1.retirar(10000.0);

    cout << "\nDespues de operaciones:\n";
    c1.mostrar();

    return 0;
}
 System.out.println("\nDespues de operaciones:");
 c1.mostrar();
 }
}
