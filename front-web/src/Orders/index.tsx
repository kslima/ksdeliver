import { useEffect, useState } from 'react';
import { fetchProducts, saveOrder } from '../api';
import { toast } from 'react-toastify';
import Footer from '../Footer';
import { checkIsSelected } from './helpers';
import OrderLocation from './OrderLocation';
import OrderSummary from './OrderSummary';
import ProductsList from './ProductsList';
import StepsHeader from './StepsHeader';
import './styles.css';
import { Product, OrderLocationData } from './types';


function Orders() {
    const [products, setProducts] = useState<Product[]>([]);
    const [selectedProducts, setSelectedProducts] = useState<Product[]>([]);
    const [orderLocation, setOrderLocation] = useState<OrderLocationData>();
    const totalPrice = selectedProducts.reduce((sum, item) => {
        return sum + item.price;
    }, 0)

    useEffect(() => {
        fetchProducts()
            .then(response => setProducts(response.data))
            .catch(error => {
                toast.warning('Erro ao listar produtos');
            });
    }, [])

    const handleSelectProduct = (product: Product) => {
        const isAlreadySelected = checkIsSelected(selectedProducts, product);

        if (isAlreadySelected) {
            const selected = selectedProducts.filter(item => item.id !== product.id);
            setSelectedProducts(selected);
        } else {
            setSelectedProducts(previous => [...previous, product]);
        }
    }

    const handleSubmit = () => {
        if (selectedProducts.length === 0 ) {
            toast.info('Selecione ao menos um produto!');
            return;
        }

        if (orderLocation?.address === null ) {
            toast.info('Informe um endereço!');
            return;
        }
        
        const productsIds = selectedProducts.map(({ id }) => ({ id }));
        const payload = {
            ...orderLocation!,
            products: productsIds
        }

        saveOrder(payload)
            .then((response) => {
                toast.success(`Pedido enviado com sucesso! Nº ${response.data.id}`);
                setSelectedProducts([]);
            })
            .catch((error) => {
                toast.warning('Erro ao enviar pedido!');
            })
    }

    return (
        <>
            <div className="orders-container">
                <StepsHeader />
                <ProductsList
                    products={products}
                    onSelectedProduct={handleSelectProduct}
                    selectedProducts={selectedProducts}
                />
                <OrderLocation onChangeLocation={location => setOrderLocation(location)} />
                <OrderSummary amount={selectedProducts.length}
                    totalPrice={totalPrice}
                    onSubmit={handleSubmit}
                />
            </div>
            <Footer />
        </>
    )
}

export default Orders;